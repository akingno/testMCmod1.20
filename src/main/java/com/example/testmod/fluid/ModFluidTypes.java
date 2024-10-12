package com.example.testmod.fluid;

import com.example.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, TestMod.MOD_ID);

    // 水流体的资源位置
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final RegistryObject<FluidType> SPRING_FLUID_TYPE = register("spring_fluid_type",
            FluidType.Properties.create()
                    .canSwim(true)
                    .canExtinguish(true)
                    .lightLevel(3)
                    .density(15)
                    .viscosity(5)
                    .sound(SoundAction.get("drink"),
                    SoundEvents.BUCKET_FILL));


    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xFFC1E2DB, new Vector3f(193f / 255f, 226f / 255f, 219f / 255f), properties));
    }


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
