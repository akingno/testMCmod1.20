package com.example.testmod.block;

import com.example.testmod.TestMod;
import com.example.testmod.block.custom.AndonBlock;
import com.example.testmod.block.custom.SoundBlock;
import com.example.testmod.block.custom.SpringFluidBlock;
import com.example.testmod.block.custom.SpringSpawner;
import com.example.testmod.fluid.ModFluids;
import com.example.testmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> ANDON = registerBlock("andon",
            () -> new AndonBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).
                    strength(1.5F, 6.0F).sound(SoundType.WOOD)
                    .lightLevel((state)-> 14).noOcclusion()));


    public static final RegistryObject<Block> PLASTER = registerBlock("plaster",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> PLASTER_STAIRS = registerBlock("plaster_stairs",
            () -> new StairBlock(()->ModBlocks.PLASTER.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ModBlocks.PLASTER.get())));
    public static final RegistryObject<Block> PLASTER_SLAB = registerBlock("plaster_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.PLASTER.get())));
    public static final RegistryObject<Block> KAWARA = registerBlock("kawara",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(2.0F, 6.0F)));
    public static final RegistryObject<Block> KAWARA_STAIRS = registerBlock("kawara_stairs",
            () -> new StairBlock(()->ModBlocks.KAWARA.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ModBlocks.KAWARA.get())));
    public static final RegistryObject<Block> KAWARA_SLAB = registerBlock("kawara_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.KAWARA.get())));
    public static final RegistryObject<Block> NAMAKO = registerBlock("namako",
            () -> new Block(BlockBehaviour.Properties.copy(ModBlocks.KAWARA.get())));
    public static final RegistryObject<Block> NAMAKO_STAIRS = registerBlock("namako_stairs",
            () -> new StairBlock(()->ModBlocks.NAMAKO.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(ModBlocks.NAMAKO.get())));
    public static final RegistryObject<Block> NAMAKO_SLAB = registerBlock("namako_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(ModBlocks.KAWARA.get())));



    public static final RegistryObject<Block> SPRING_SPAWNER = registerBlock("spring_spawner",
            () -> new SpringSpawner(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)
                    .strength(2.0f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final RegistryObject<LiquidBlock> SPRING_BLOCK = BLOCKS.register("spring_block",
            () -> new SpringFluidBlock());





    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
