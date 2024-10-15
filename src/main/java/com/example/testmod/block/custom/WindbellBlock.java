package com.example.testmod.block.custom;

import com.example.testmod.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;


public class WindbellBlock extends Block {
    private static final VoxelShape PART1 = Block.box(7.5, 13, 7.5, 8.5, 16, 8.5);
    private static final VoxelShape PART2 = Block.box(7.5, 8, 7.5, 8.5, 9, 8.5);
    private static final VoxelShape PART3 = Block.box(6, 10, 6, 10, 12, 10);
    private static final VoxelShape PART4 = Block.box(7, 12, 7, 9, 13, 9);
    private static final VoxelShape PART5 = Block.box(6.5, 9, 6.5, 9.5, 10, 9.5);
    private static final VoxelShape PART6 = Block.box(7, 2.75, 8, 9, 8.25, 8);

    public WindbellBlock(Properties properties) {
        super(properties);
    }
    private static final VoxelShape FULL_SHAPE = Shapes.or(PART1, PART2, PART3, PART4, PART5, PART6);

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 随机播放声音
        if (random.nextFloat() < 0.05f) { // 0.05% 的几率播放声音
            level.playSound(null, pos, ModSounds.WINDBELL_SOUND.get(), SoundSource.BLOCKS, 0.5f, 1f);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return FULL_SHAPE;
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hit) {
        //player.sendSystemMessage(Component.literal("Windbell~~~"));
        // 播放声音

        level.playSound(player, pos, ModSounds.WINDBELL_SOUND.get(), SoundSource.BLOCKS, 1.0f, 1.5f);

        return InteractionResult.SUCCESS;
    }
}
