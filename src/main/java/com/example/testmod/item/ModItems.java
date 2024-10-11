package com.example.testmod.item;

import com.example.testmod.TestMod;
import com.example.testmod.item.custom.MeshiItem;
import com.example.testmod.item.custom.MetalDetectorItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAMBOO_BUCKET = ITEMS.register("bamboo_bucket",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MESHI = ITEMS.register("meshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.MESHI).stacksTo(1)));


    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
