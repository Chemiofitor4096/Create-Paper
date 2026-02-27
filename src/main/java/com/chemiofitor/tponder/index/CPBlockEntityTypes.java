package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.common.block.entity.PapermakingFrameBlockEntity;
import com.simibubi.create.content.logistics.depot.DepotRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

public class CPBlockEntityTypes {
    public static final BlockEntityEntry<PapermakingFrameBlockEntity> DEPOT = REGISTRATE
            .blockEntity("papermaking_frame", PapermakingFrameBlockEntity::new)
            .validBlocks(CPBlocks.PAPERMAKING_FRAME)
            .renderer(() -> DepotRenderer::new)
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Block Entity Types initialized");
    }
}
