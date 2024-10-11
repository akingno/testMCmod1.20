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
            if (world.isEmptyBlock(pos.above())) {
                //world.setBlock(pos.above(), ModBlocks.HOT_SPRING_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                world.setBlock(pos.above(), Blocks.WATER.defaultBlockState(), Block.UPDATE_ALL);
            } else {
                //Set<BlockPos> posSet = getFluidToList(world, pos.above(), MeshiFluids.HOT_SPRING.get(), 100 * lvl);
                Set<BlockPos> posSet = getFluidToList(world, pos.above(), Fluids.WATER, 100 * lvl);
                posSet.removeIf(px -> world.getFluidState(px).isEmpty());
                int count = 0;
                Iterator<BlockPos> iterator = posSet.iterator();

                while (iterator.hasNext()) {
                    BlockPos p = iterator.next();
                    if (world.getFluidState(p).getAmount() == 7 && world.getFluidState(p.below()).isEmpty()) {

                        //world.setBlock(p.above(), ModBlocks.HOT_SPRING_BLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                        world.setBlock(pos.above(), Blocks.WATER.defaultBlockState(), Block.UPDATE_ALL);
                        count++;
                        if (count > lvl * 2) {
                            break;
                        }
                    }
                }

                if (count == 0) {

                    world.setBlock(pos, state.setValue(LEVEL, 0), Block.UPDATE_ALL);
                }
            }
            world.scheduleTick(pos, this, 100);
        }
    }

    public static Set<BlockPos> getFluidToList(Level world, BlockPos pos, Fluid fluid, int limit) {
        List<BlockPos> nextTargets = new ArrayList<>();
        nextTargets.add(pos);
        Set<BlockPos> foundPositions = new LinkedHashSet<>();

        do {
            Stream<BlockPos> stream = nextTargets.stream().flatMap(target ->
                            Arrays.stream(Direction.values())
                                    .filter(d -> d != Direction.UP)
                                    .map(target::relative)
                    ).filter(fixedPos -> world.getFluidState(fixedPos).getType().isSame(fluid))
                    .limit(limit - foundPositions.size());

            nextTargets = stream.filter(foundPositions::add).collect(Collectors.toList());
        } while (foundPositions.size() <= limit && !nextTargets.isEmpty());

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
            if (player.getItemInHand(hand).is(Items.BUCKET)) {
                player.sendSystemMessage(Component.literal("Using with bucket"));

                //Set<BlockPos> posSet = getFluidToList(world, pos.above(), MeshiFluids.HOT_SPRING.get(), 300);
                Set<BlockPos> posSet = getFluidToList(world, pos.above(), Fluids.WATER, 300);

                posSet.removeIf(px -> world.getFluidState(px).isEmpty());
                for (BlockPos p : posSet) {

                    world.setBlock(p, world.getBlockState(p).setValue(LEVEL, 7), Block.UPDATE_ALL);
                }

                world.setBlock(pos, state.setValue(LEVEL, 0), Block.UPDATE_ALL);
                return InteractionResult.SUCCESS;
            }

            state = state.cycle(LEVEL);
            int lvl = state.getValue(LEVEL);
            world.setBlock(pos, state, 3);
            if (lvl > 0) {
                world.scheduleTick(pos, this, 100);
            }
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }
}
