package com.example.testmod.fluid;

import com.example.testmod.fluid.HotSpring;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.example.testmod.TestMod;

public class MeshiFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TestMod.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TestMod.MOD_ID);

    // 创建自定义 FluidType
    public static final RegistryObject<FluidType> HOT_SPRING_FLUID_TYPE = FLUID_TYPES.register("hot_spring_fluid_type",
            () -> new FluidType(FluidType.Properties.create()
                    .lightLevel(10)
                    .density(1000)
                    .viscosity(1000)
                    .canSwim(true)
                    .fallDistanceModifier(0.5f) // 可选的其他设置
                    ));

    // 注册静态和流动的温泉流体
    public static final RegistryObject<ForgeFlowingFluid> HOT_SPRING = FLUIDS.register("hot_spring", HotSpring.Source::new);
    public static final RegistryObject<ForgeFlowingFluid> FLOWING_HOT_SPRING = FLUIDS.register("flowing_hot_spring", HotSpring.Flowing::new);
}
