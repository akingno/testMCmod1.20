package com.akingno.sakuraak.fluid;

import com.akingno.sakuraak.SakuraAk;
import com.akingno.sakuraak.block.ModBlocks;
import com.akingno.sakuraak.item.ModItems;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Flow;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class ModFluids {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, SakuraAk.MOD_ID);

    public static final RegistryObject<FlowingFluid> SPRING_FLUID = FLUIDS.register("spring_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.SPRING_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SPRING_FLOWING = FLUIDS.register("spring_flowing",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.SPRING_PROPERTIES));

    public static final RegistryObject<LiquidBlock> SPRING_BLOCK = ModBlocks.BLOCKS.register("spring",
            () -> new SpringFluidBlock(ModFluids.SPRING_FLUID, BlockBehaviour.Properties.copy(Blocks.WATER).
                    strength(100f)));

    public static final ForgeFlowingFluid.Properties SPRING_PROPERTIES = createProp(SPRING_FLUID, SPRING_FLOWING, FluidTypeRegistry.SPRING_TYPE, SPRING_BLOCK, ModItems.SPRING_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }

    private static ForgeFlowingFluid.Properties createProp(
            Supplier<? extends Fluid> still,
            Supplier<? extends Fluid> flowing,
            RegistryObject<FluidType> fluidType,
            Supplier<? extends LiquidBlock> block,
            Supplier<? extends Item> bucket){

        UnaryOperator<ForgeFlowingFluid.Properties> blockProperties = p->p.block(block).bucket(bucket)
                .slopeFindDistance(4).levelDecreasePerBlock(1).tickRate(5).explosionResistance(100F);
        return blockProperties.apply(new ForgeFlowingFluid.Properties(fluidType ,still, flowing));
    }
}
