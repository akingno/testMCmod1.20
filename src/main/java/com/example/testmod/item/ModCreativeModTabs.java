package com.example.testmod.item;

import com.example.testmod.TestMod;
import com.example.testmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);



    public static final RegistryObject<CreativeModeTab> MESHI_TAB = CREATIVE_MODE_TABS.register("meshi_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.BAMBOO))
                    .title(Component.translatable("creativetab.meshi_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Items.BAMBOO);
                        pOutput.accept(ModItems.BAMBOO_BUCKET.get());
                        pOutput.accept(ModItems.MESHI.get());
                        pOutput.accept(ModBlocks.SPRING_SPAWNER.get());
                        pOutput.accept(ModItems.METAL_DETECTOR.get());
                        pOutput.accept(ModItems.SPRING_BUCKET.get());
                        pOutput.accept(ModItems.TUDURA.get());
                        pOutput.accept(ModItems.BAMBOO_SHOOT.get());

                        pOutput.accept(ModItems.GYUMESHI.get());
                        pOutput.accept(ModItems.KATSUMESHI.get());
                        pOutput.accept(ModItems.KINOKOMESHI.get());
                        pOutput.accept(ModItems.EGGMESHI.get());
                        pOutput.accept(ModItems.OYAKODON.get());
                        pOutput.accept(ModItems.TEKKA.get());
                        pOutput.accept(ModItems.TAKEDON.get());

                        pOutput.accept(ModBlocks.KAWARA.get());
                        pOutput.accept(ModBlocks.KAWARA_STAIRS.get());
                        pOutput.accept(ModBlocks.KAWARA_SLAB.get());
                        pOutput.accept(ModBlocks.NAMAKO.get());
                        pOutput.accept(ModBlocks.NAMAKO_STAIRS.get());
                        pOutput.accept(ModBlocks.NAMAKO_SLAB.get());
                        pOutput.accept(ModBlocks.PLASTER.get());
                        pOutput.accept(ModBlocks.PLASTER_STAIRS.get());
                        pOutput.accept(ModBlocks.ANDON.get());
                        pOutput.accept(ModBlocks.WINDBELL.get());
                        pOutput.accept(ModBlocks.WATERWHEEL.get());


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
