package com.example.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.example.testmod.fluid.ModFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class SpringFluidBlock extends LiquidBlock {

    public SpringFluidBlock() {
        super(ModFluids.SPRING_FLUID_SOURCE, Properties.copy(net.minecraft.world.level.block.Blocks.WATER).lightLevel((state)->3));
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {


        // 蒸汽效果：如果上方是空气，则有 5% 概率生成蒸汽粒子
        if (world.isEmptyBlock(pos.above()) && rand.nextFloat() < 0.1f) {
            double x = pos.getX() + rand.nextFloat();
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + rand.nextFloat();
            float color = 1.0f;
            Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE,
                    x, y, z, 0.0, 0.0, 0.0).setColor(color, color, color);
        }

        // 气泡效果：流体按 2% 概率生成气泡
        if (rand.nextFloat() < 0.02F) {
            double d0 = pos.getX() + rand.nextFloat();
            double d1 = pos.getY() + 0.95;
            double d2 = pos.getZ() + rand.nextFloat();

            Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE_POP,
                    d0, d1, d2, 0.0, 0, 0.0);
        }
    }
}
