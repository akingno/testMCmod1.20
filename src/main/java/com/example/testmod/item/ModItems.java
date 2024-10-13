package com.example.testmod.item;

import com.example.testmod.TestMod;
import com.example.testmod.fluid.ModFluids;
import com.example.testmod.item.custom.BambooShootItem;
import com.example.testmod.item.custom.MeshiItem;
import com.example.testmod.item.custom.MetalDetectorItem;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> BAMBOO_BUCKET = ITEMS.register("bamboo_bucket",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BAMBOO_SHOOT = ITEMS.register("bamboo_shoot",
            () -> new BambooShootItem(new Item.Properties()));

    public static final RegistryObject<Item> TUDURA = ITEMS.register("tudura",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SPRING_BUCKET = ITEMS.register("spring_bucket",
            () -> new BucketItem(ModFluids.SPRING_FLUID_SOURCE, new Item.Properties()
                    .craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> MESHI = ITEMS.register("meshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.MESHI).stacksTo(64)));
    public static final RegistryObject<Item> GYUMESHI = ITEMS.register("gyumeshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.GYUMESHI).stacksTo(64)));
    public static final RegistryObject<Item> KATSUMESHI = ITEMS.register("katsumeshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.KATSUMESHI).stacksTo(64)));
    public static final RegistryObject<Item> KINOKOMESHI = ITEMS.register("kinokomeshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.KINOKOMESHI).stacksTo(64)));
    public static final RegistryObject<Item> EGGMESHI = ITEMS.register("eggmeshi",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.EGGMESHI).stacksTo(64)));
    public static final RegistryObject<Item> OYAKODON = ITEMS.register("oyakodon",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.OYAKODON).stacksTo(64)));
    public static final RegistryObject<Item> TEKKA = ITEMS.register("tekka",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.TEKKA).stacksTo(64)));
    public static final RegistryObject<Item> TAKEDON = ITEMS.register("takedon",
            () -> new MeshiItem(new Item.Properties().food(ModFoods.TAKEDON).stacksTo(64)));



    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
