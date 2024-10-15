package com.example.testmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TestMod.MOD_ID);

    // 注册 windbell 声音
    public static final RegistryObject<SoundEvent> WINDBELL_SOUND = SOUNDS.register("windbell",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TestMod.MOD_ID, "windbell")));

    // 调用该方法以便在主类中注册声音
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
