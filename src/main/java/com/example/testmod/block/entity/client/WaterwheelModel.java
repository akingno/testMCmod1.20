package com.example.testmod.block.entity.client;

import com.example.testmod.TestMod;
import com.example.testmod.block.entity.WaterwheelBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WaterwheelModel extends GeoModel<WaterwheelBlockEntity> {
    @Override
    public ResourceLocation getModelResource(WaterwheelBlockEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/waterwheel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WaterwheelBlockEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/block/animate_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WaterwheelBlockEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/waterwheel.animation.json");
    }
}
