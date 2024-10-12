package com.example.testmod.fluid;

import com.example.testmod.TestMod;
import com.example.testmod.block.ModBlocks;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, TestMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SPRING_FLUID_SOURCE = FLUIDS.register("spring_fluid_source",
            () -> new ForgeFlowingFluid.Source(ModFluids.SPRING_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SPRING_WATER = FLUIDS.register("flowing_spring_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SPRING_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties SPRING_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.SPRING_FLUID_TYPE, SPRING_FLUID_SOURCE, FLOWING_SPRING_WATER)
            .slopeFindDistance(10)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.SPRING_BLOCK)
            .tickRate(10);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
