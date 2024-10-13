package com.example.testmod.block.custom;


import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

//会朝向玩家
public class PlayerFacingBlock extends HorizontalDirectionalBlock {
    // 定义一个属性，表示方块朝向的方向
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public PlayerFacingBlock(BlockBehaviour.Properties properties) {
        super(properties);
        // 设置默认状态为面朝北方
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // 获取方块放置的状态，自动设定朝向
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 根据玩家面对的方向放置方块
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    // 定义方块状态属性
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING); // 将 FACING 属性加入状态定义
    }
}








