package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class CPPartialModels {
    public static final PartialModel MECHANICAL_PULPER_POLE = block("mechanical_pulper/pole"),
            MECHANICAL_PULPER_HEAD = block("mechanical_pulper/head");

    private static PartialModel block(String path) {
        return PartialModel.of(CreatePaper.asResource("block/" + path));
    }

    private static PartialModel entity(String path) {
        return PartialModel.of(CreatePaper.asResource("entity/" + path));
    }

    public static void init() {}
}
