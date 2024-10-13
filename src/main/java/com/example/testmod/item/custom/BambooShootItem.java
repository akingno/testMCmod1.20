package com.example.testmod.item.custom;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.Level;

public class BambooShootItem extends Item {
    public BambooShootItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().above();  // 获取放置位置

        // 检查是否为服务器端和放置位置是否为空气方块
        if (level instanceof ServerLevel serverLevel && level.isEmptyBlock(pos)) {
            // 创建竹笋方块的状态，设置 age=0 使其为竹笋状态
            BlockState bambooState = Blocks.BAMBOO_SAPLING.defaultBlockState();
            serverLevel.setBlockAndUpdate(pos, bambooState);
            level.gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, pos);

            // 如果玩家不是创造模式，减少物品数量
            if (!context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}
