package com.akingno.sakuraak;

import com.akingno.sakuraak.block.ModBlocks;
import com.akingno.sakuraak.client.particle.ModParticles;
import com.akingno.sakuraak.fluid.FluidTypeRegistry;
import com.akingno.sakuraak.fluid.ModFluids;
import com.akingno.sakuraak.item.ModCreativeModTabs;
import com.akingno.sakuraak.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(SakuraAk.MOD_ID)
public class SakuraAk
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "sakuraak";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace



    public SakuraAk()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModFluids.register(modEventBus);
        ModParticles.register(modEventBus);
        FluidTypeRegistry.FLUID_TYPES.register(modEventBus);



        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        //modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.GLASS_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.HUSUMA.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SAKURA_LEAVES.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.SAKURA_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ANDON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.VINI_BOOK.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FUTON.get(), RenderType.cutout());


            ItemBlockRenderTypes.setRenderLayer(ModFluids.SPRING_FLUID.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SPRING_FLOWING.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SPRING_BLOCK.get(), RenderType.translucent());
        }

    }

}
