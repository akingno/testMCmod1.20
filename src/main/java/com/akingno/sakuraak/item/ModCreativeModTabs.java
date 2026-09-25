package com.akingno.sakuraak.item;

import com.akingno.sakuraak.SakuraAk;
import com.akingno.sakuraak.block.ModBlocks;
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
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SakuraAk.MOD_ID);



    public static final RegistryObject<CreativeModeTab> MESHI_TAB = CREATIVE_MODE_TABS.register("meshi_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.BAMBOO))
                    .title(Component.translatable("creativetab.meshi_tab"))
                    .displayItems((params, output) -> {

                        // --- Vanilla helper ---
                        output.accept(Items.BAMBOO);

                        // --- Items / Foods ---
                        output.accept(ModItems.BAMBOO_BUCKET.get());
                        output.accept(ModItems.SPRING_BUCKET.get());
                        output.accept(ModItems.BAMBOO_SHOOT.get());
                        output.accept(ModItems.TUDURA.get());

                        output.accept(ModItems.MESHI.get());
                        output.accept(ModItems.ICEGLASS.get());

                        output.accept(ModItems.GYUMESHI.get());
                        output.accept(ModItems.KATSUMESHI.get());
                        output.accept(ModItems.KINOKOMESHI.get());
                        output.accept(ModItems.EGGMESHI.get());
                        output.accept(ModItems.OYAKODON.get());
                        output.accept(ModItems.TEKKA.get());
                        output.accept(ModItems.TAKEDON.get());

                        // --- Tools / Weapons ---
                        output.accept(ModItems.KATANA.get());

                        // --- Building blocks: stone/plaster ---
                        output.accept(ModBlocks.PLASTER.get());
                        output.accept(ModBlocks.PLASTER_STAIRS.get());
                        output.accept(ModBlocks.PLASTER_SLAB.get());

                        output.accept(ModBlocks.KAWARA.get());
                        output.accept(ModBlocks.KAWARA_STAIRS.get());
                        output.accept(ModBlocks.KAWARA_SLAB.get());

                        output.accept(ModBlocks.NAMAKO.get());
                        output.accept(ModBlocks.NAMAKO_STAIRS.get());
                        output.accept(ModBlocks.NAMAKO_SLAB.get());

                        // --- Sakura wood set ---
                        output.accept(ModBlocks.SAKURA_LOG.get());
                        output.accept(ModBlocks.SAKURA_LOG_STAIRS.get());
                        output.accept(ModBlocks.SAKURA_LOG_SLAB.get());

                        output.accept(ModBlocks.SAKURA_PLANKS.get());
                        output.accept(ModBlocks.SAKURA_STAIRS.get());
                        output.accept(ModBlocks.SAKURA_SLAB.get());

                        output.accept(ModBlocks.SAKURA_LEAVES.get());
                        output.accept(ModBlocks.SAKURA_SAPLING.get());

                        // --- Japanese-style interior blocks ---
                        output.accept(ModBlocks.TATAMI.get());
                        output.accept(ModBlocks.TATAMI_NB.get());

                        output.accept(ModBlocks.ANDON.get());
                        output.accept(ModBlocks.VINI_BOOK.get());

                        output.accept(ModBlocks.GLASS_DOOR.get());
                        output.accept(ModBlocks.HUSUMA.get());

                        output.accept(ModBlocks.FUTON.get());
                    })
                    .build()
    );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
