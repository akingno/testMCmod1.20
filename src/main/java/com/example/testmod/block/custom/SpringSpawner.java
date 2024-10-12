package com.example.testmod.block.custom;

import com.example.testmod.block.ModBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.core.particles.ParticleTypes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpringSpawner extends Block {
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;
    public static Boolean IsActive = false;

    public SpringSpawner(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
    }

    public boolean hasLevel(BlockState state) {
        return state.getValue(LEVEL) > 0;
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        int lvl = state.getValue(LEVEL);

        if (lvl > 0) {
            // 获取符合条件的流动水和空气方块
            Set<BlockPos> posSet = getFluidToList(world, pos.above(), Fluids.WATER, 300);
            posSet.removeIf(px -> !world.isEmptyBlock(px) && world.getFluidState(px).isSource()); // 排除非空方块和水源

            // 逐步提升水位
            for (BlockPos p : posSet) {
                BlockState currentState = world.getBlockState(p);
                int currentLevel = currentState.hasProperty(BlockStateProperties.LEVEL)
                        ? currentState.getValue(BlockStateProperties.LEVEL)
                        : 8; // 8 表示非水方块

                if (currentLevel == 8) {
                    // 空方块 -> 转为流动水 LEVEL 6
                    world.setBlock(p, Blocks.WATER.defaultBlockState().setValue(BlockStateProperties.LEVEL, 6), Block.UPDATE_ALL);
                } else if (currentLevel > 1) {
                    // 流动水逐步上涨 -> LEVEL 减小
                    world.setBlock(p, currentState.setValue(BlockStateProperties.LEVEL, currentLevel - 1), Block.UPDATE_ALL);
                } else if (currentLevel == 1) {
                    // LEVEL 1 流动水 -> 转为水源方块
                    world.setBlock(p, Blocks.WATER.defaultBlockState().setValue(BlockStateProperties.LEVEL, 0), Block.UPDATE_ALL);
                }
            }

            // 确保定时调用 tick，以便持续上升水位
            world.scheduleTick(pos, this, 3);
        }
    }




    public static Set<BlockPos> getFluidToList(Level world, BlockPos pos, Fluid fluid, int limit) {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> foundPositions = new HashSet<>();
        queue.add(pos);

        while (!queue.isEmpty() && foundPositions.size() < limit) {
            BlockPos currentPos = queue.poll();

            // 判断是否为空方块或流动水，确保只遍历同层
            if (currentPos.getY() == pos.getY() &&
                    (world.isEmptyBlock(currentPos) ||
                            (world.getFluidState(currentPos).getType().isSame(fluid) && !world.getFluidState(currentPos).isSource()))) {

                foundPositions.add(currentPos);

                // 遍历水平相邻的四个方向
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos nextPos = currentPos.relative(direction);

                    // 如果是空方块或流动水，且未被访问过，则添加到队列中
                    if (!foundPositions.contains(nextPos) &&
                            (world.isEmptyBlock(nextPos) ||
                                    (world.getFluidState(nextPos).getType().isSame(fluid) && !world.getFluidState(nextPos).isSource()))) {

                        queue.add(nextPos);
                    }
                }
            }
        }
        return foundPositions;
    }

    public static Set<BlockPos> getWaterToList(Level world, BlockPos pos, Fluid fluid, int limit) {
        Queue<BlockPos> queue = new LinkedList<>();
        Set<BlockPos> foundPositions = new HashSet<>();
        queue.add(pos);

        while (!queue.isEmpty() && foundPositions.size() < limit) {
            BlockPos currentPos = queue.poll();

            // 确保方块为同层水源方块
            if (currentPos.getY() == pos.getY() && world.getFluidState(currentPos).isSource()) {
                foundPositions.add(currentPos);

                // 遍历四个方向，排除上下方块
                for (Direction direction : Direction.Plane.HORIZONTAL) {
                    BlockPos nextPos = currentPos.relative(direction);
                    if (!foundPositions.contains(nextPos) && world.getFluidState(nextPos).getType().isSame(fluid)
                            && world.getFluidState(nextPos).isSource()) {
                        queue.add(nextPos);
                    }
                }
            }
        }
        return foundPositions;
    }




    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
        super.animateTick(state, world, pos, rand);
        int lvl = state.getValue(LEVEL);
        if (lvl > 0) {
            for (int i = 0; i < 5 * lvl; i++) {
                double d0 = (double) pos.getX() + rand.nextFloat();
                double d1 = (double) (pos.getY() + 1) + rand.nextFloat();
                double d2 = (double) pos.getZ() + rand.nextFloat();
                Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE, d0, d1, d2, 0.0, lvl * 0.1, 0.0);
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            player.sendSystemMessage(Component.literal("Using"));

            if (!IsActive) {
                IsActive = true;
                player.sendSystemMessage(Component.literal("Turn on Spring"));

                // 获取上方层内的空方块和流动水方块位置
                Set<BlockPos> posSet = getFluidToList(world, pos.above(), Fluids.WATER, 300);
                posSet.removeIf(px -> !world.isEmptyBlock(px) || world.getFluidState(px).isSource()); // 保留空方块和流动水

                // 将符合条件的位置设置为流动水，初始水位为 LEVEL 6
                for (BlockPos p : posSet) {
                    world.setBlock(p, Blocks.WATER.defaultBlockState().setValue(BlockStateProperties.LEVEL, 5), Block.UPDATE_ALL);
                }

                // 启动 tick，开始逐步提升水位
                world.setBlock(pos, state.setValue(LEVEL, 1), Block.UPDATE_ALL);
                world.scheduleTick(pos, this, 1);

                return InteractionResult.SUCCESS;
            }
            else{
                //如果已经开启，那么把其变为关闭，并遍历所有水方块变为空气方块

                IsActive = false;
                player.sendSystemMessage(Component.literal("Turn off Spring"));
                Set<BlockPos> posSet = getWaterToList(world, pos.above(), Fluids.WATER, 300);

                // 将所有水源方块转换为流动水
                for (BlockPos p : posSet) {
                    world.setBlock(p, Blocks.WATER.defaultBlockState().setValue(BlockStateProperties.LEVEL, 1), Block.UPDATE_ALL);
                }

                world.setBlock(pos, state.setValue(LEVEL, 0), Block.UPDATE_ALL);
                return InteractionResult.SUCCESS;
            }

            /*state = state.cycle(LEVEL);
            int lvl = state.getValue(LEVEL);
            world.setBlock(pos, state, 3);
            if (lvl > 0) {
                world.scheduleTick(pos, this, 100);
            }*/
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
