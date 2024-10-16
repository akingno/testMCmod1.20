package com.example.testmod.block.custom;

import com.example.testmod.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class WaterwheelBlock extends PlayerFacingBlock {
    public static final BooleanProperty ROTATING = BooleanProperty.create("rotating");
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public WaterwheelBlock(Properties properties) {
        super(properties);
        // 设置默认状态，包含 ROTATING 和 FACING
        this.registerDefaultState(this.stateDefinition.any().setValue(ROTATING, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        updateRotationState(level, pos, state);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        updateRotationState(level, pos, state);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 在放置方块时设置 FACING 属性
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    private void updateRotationState(Level level, BlockPos pos, BlockState state) {
        BlockState belowState = level.getBlockState(pos.below());

        // 检查下方是否有水，并决定是否应该旋转
        boolean shouldRotate = belowState.is(Blocks.WATER) || belowState.is(Blocks.WATER_CAULDRON) ||
                belowState.is(ModBlocks.SPRING_BLOCK.get());

        if (state.getValue(ROTATING) != shouldRotate) {
            level.setBlock(pos, state.setValue(ROTATING, shouldRotate), 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // 添加 ROTATING 和 FACING 属性
        builder.add(ROTATING, FACING);
    }
}
