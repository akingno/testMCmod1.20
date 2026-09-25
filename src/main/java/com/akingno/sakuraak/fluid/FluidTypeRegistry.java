package com.akingno.sakuraak.fluid;

import com.akingno.sakuraak.SakuraAk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;


public class FluidTypeRegistry {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, SakuraAk.MOD_ID);

    public static final RegistryObject<FluidType> SPRING_TYPE = register("spring_fluid",0xFFBFEFFF);

    private static RegistryObject<FluidType> register(String name,int color){
        return FLUID_TYPES.register(name,()->create(color));
    }

    private static FluidType create(int color){
        return new FluidType(FluidType.Properties.create()
                .temperature(330)
                .canConvertToSource(true)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .lightLevel(10)) {

            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return ModFluids.WATER_STILL_RL;   // 或你自己的 spring_still
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return ModFluids.WATER_FLOWING_RL; // 或你自己的 spring_flow
                    }

                    @Override
                    public ResourceLocation getOverlayTexture() {
                        return ModFluids.WATER_OVERLAY_RL;
                    }

                    @Override
                    public int getTintColor() {
                        return color; // 例如 0xFFBFEFFF
                    }
                });
            }
        };
    }
}
