package com.chemiofitor.tponder.index;

import com.simibubi.create.infrastructure.worldgen.LayerPattern;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.world.level.block.Blocks;

public class CPLayerPatterns {
    public static final NonNullSupplier<LayerPattern> COAL_GANGUE = () -> LayerPattern.builder()
            .layer(l -> l.weight(2)
                    .block(CPBlocks.COAL_GANGUE.get())
                    .size(2, 5))
            .layer(l -> l.weight(2)
                    .block(Blocks.TUFF)
                    .block(Blocks.DEEPSLATE)
                    .size(2, 3))
            .layer(l -> l.weight(2)
                    .block(Blocks.DRIPSTONE_BLOCK)
                    .size(1, 2))
            .build();
}
