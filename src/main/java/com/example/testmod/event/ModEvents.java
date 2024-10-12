package com.example.testmod.event;

import com.example.testmod.fluid.ModFluids;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "testmod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEvents {

    private static final int CHECK_INTERVAL = 100; // 每 20 tick 执行一次检测
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.level().isClientSide) { // 仅在服务器端执行
            tickCounter++;

            // 检查是否达到设定的 tick 间隔
            if (tickCounter >= CHECK_INTERVAL) {
                tickCounter = 0; // 重置计数器

                // 检查玩家当前所处的流体是否是温泉
                if (event.player.isInFluidType(ModFluids.SPRING_FLUID_SOURCE.get().getFluidType())) {

                    event.player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,
                            100, 0, false, false, true));
                }
            }
        }
    }
    public static void register(IEventBus eventBus) {
        MinecraftForge.EVENT_BUS.register(eventBus);
    }
}
