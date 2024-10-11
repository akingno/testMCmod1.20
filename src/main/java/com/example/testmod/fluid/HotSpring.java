package com.example.testmod.fluid;

import com.example.testmod.block.ModBlocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class HotSpring extends ForgeFlowingFluid {

    protected HotSpring() {
        super(new ForgeFlowingFluid.Properties(
                MeshiFluids.HOT_SPRING_FLUID_TYPE, // 引用自定义 FluidType
                MeshiFluids.HOT_SPRING,
                MeshiFluids.FLOWING_HOT_SPRING)
                .block(() -> (LiquidBlock) ModBlocks.HOT_SPRING_BLOCK.get()));
    }

    public static class Source extends HotSpring {
        @Override
        public boolean isSource(FluidState state) {
            return true;
        }

        @Override
        public int getAmount(FluidState state) {
            return 8;
        }
    }

    public static class Flowing extends HotSpring {
        @Override
        public boolean isSource(FluidState state) {
            return false;
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }
    }
}
