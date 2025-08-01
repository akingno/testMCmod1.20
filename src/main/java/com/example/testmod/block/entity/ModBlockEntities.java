package com.example.testmod.block.entity;

import com.example.testmod.TestMod;
import com.example.testmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MOD_ID);

    //注册水车方块的 BlockEntity
    public static final RegistryObject<BlockEntityType<WaterwheelBlockEntity>> WATERWHEEL_ENTITY =
            BLOCK_ENTITIES.register("waterwheel",
                    () -> BlockEntityType.Builder.of(WaterwheelBlockEntity::new, ModBlocks.WATERWHEEL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
