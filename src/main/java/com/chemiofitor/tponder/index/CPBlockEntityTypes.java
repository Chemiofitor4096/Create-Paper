package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.client.renderer.MechanicalPulperRenderer;
import com.chemiofitor.tponder.client.visual.PulpVisual;
import com.chemiofitor.tponder.common.block.entity.MechanicalPulperBlockEntity;
import com.chemiofitor.tponder.common.block.entity.PapermakingDepotBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

public class CPBlockEntityTypes {
    public static final BlockEntityEntry<PapermakingDepotBlockEntity> DEPOT = REGISTRATE
            .blockEntity("papermaking_frame", PapermakingDepotBlockEntity::new)
            .validBlocks(CPBlocks.PAPERMAKING_DEPOT)
            .renderer(() -> DepotRenderer::new)
            .register();

    public static final BlockEntityEntry<MechanicalPulperBlockEntity> MECHANICAL_PULPER = REGISTRATE
            .blockEntity("mechanical_pulper", MechanicalPulperBlockEntity::new)
            .visual(() -> PulpVisual::new)
            .validBlocks(CPBlocks.MECHANICAL_PULPER)
            .renderer(() -> MechanicalPulperRenderer::new)
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Block Entity Types initialized");
    }
}
