package com.akingno.sakuraak.block.custom;

import com.akingno.sakuraak.world.gen.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SakuraTreeGrower extends AbstractTreeGrower {

    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean pHasFlowers) {
        // Match oak's occasional fancy tree; space checks still apply during growth.
        return random.nextInt(10) == 0
                ? ModConfiguredFeatures.FANCY_SAKURA_KEY
                : ModConfiguredFeatures.SAKURA_KEY;
    }

}
