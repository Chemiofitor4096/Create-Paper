package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.simibubi.create.infrastructure.worldgen.AllFeatures;
import com.simibubi.create.infrastructure.worldgen.LayerPattern;
import com.simibubi.create.infrastructure.worldgen.LayeredOreConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.List;

import static com.simibubi.create.infrastructure.worldgen.AllConfiguredFeatures.STRIATED_ORES_OVERWORLD;
import static net.minecraft.data.worldgen.features.FeatureUtils.register;

public class CPConfiguredFeatures {
    private static ResourceKey<ConfiguredFeature<?, ?>> key(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CreatePaper.asResource(name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> ctx) {

        List<LayerPattern> overworldLayerPatterns = List.of(
                CPLayerPatterns.COAL_GANGUE.get()
        );

        register(ctx, STRIATED_ORES_OVERWORLD, AllFeatures.LAYERED_ORE.get(), new LayeredOreConfiguration(overworldLayerPatterns, 32, 0));
    }
}
