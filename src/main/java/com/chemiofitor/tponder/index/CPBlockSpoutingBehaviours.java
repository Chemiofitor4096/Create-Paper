package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.common.block.behavior.PulpSpoutingBehaviour;
import com.simibubi.create.api.behaviour.spouting.BlockSpoutingBehaviour;

public class CPBlockSpoutingBehaviours {
    public static void register() {
        BlockSpoutingBehaviour.BY_BLOCK.register(CPBlocks.PAPERMAKING_DEPOT.get(), new PulpSpoutingBehaviour());
    }
}
