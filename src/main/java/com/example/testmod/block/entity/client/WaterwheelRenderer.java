package com.example.testmod.block.entity.client;

import com.example.testmod.block.entity.WaterwheelBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class WaterwheelRenderer extends GeoBlockRenderer<WaterwheelBlockEntity> {

    public WaterwheelRenderer(BlockEntityRendererProvider.Context context) {
        super(new WaterwheelModel());
    }
}
