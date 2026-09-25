package com.akingno.sakuraak.fluid;


import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraftforge.common.property.Properties;

import java.util.function.Supplier;

public class SpringFluidBlock extends LiquidBlock {

    public SpringFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties props) {
        super(supplier, props);
    }

    /**
     * 客户端：随机显示粒子（蒸汽/气泡）
     */
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
        super.animateTick(state, level, pos, rand);

        // 仅客户端
        if (!level.isClientSide()) return;

        // 只在“水面”生成蒸汽：上方是空气
        if (level.isEmptyBlock(pos.above())) {
            if (rand.nextFloat() < 0.10f) {
                double x = pos.getX() + rand.nextDouble();
                double y = pos.getY() + 1.02D;
                double z = pos.getZ() + rand.nextDouble();

                double vx = (rand.nextDouble() - 0.5D) * 0.01D;
                double vy = 0.03D + rand.nextDouble() * 0.02D;
                double vz = (rand.nextDouble() - 0.5D) * 0.01D;

                // CLOUD 更像温泉蒸汽；你也可以换 SMOKE
                level.addParticle(ParticleTypes.CLOUD, x, y, z, vx, vy, vz);
            }
        }

        // 气泡：2% 概率
        if (rand.nextFloat() < 0.02F) {
            double x = pos.getX() + rand.nextDouble();
            double y = pos.getY() + 0.95D;
            double z = pos.getZ() + rand.nextDouble();

            level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * 服务端：实体在流体方块里时触发（上 Buff / 回血等）
     */
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);

        // 仅服务端处理 Buff
        if (level.isClientSide()) return;
        if (!(entity instanceof LivingEntity living)) return;

        // 只对玩家生效
        if (!(living instanceof Player)) return;

        // 每秒触发一次
        if (living.tickCount % 20 == 0) {
            // Regeneration I 持续 3 秒
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 0, true, false, false));
        }
    }
}
