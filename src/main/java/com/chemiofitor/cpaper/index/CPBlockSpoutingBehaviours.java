package com.chemiofitor.cpaper.index;

import com.chemiofitor.cpaper.common.block.behavior.PulpSpoutingBehaviour;
import com.simibubi.create.api.behaviour.spouting.BlockSpoutingBehaviour;

public class CPBlockSpoutingBehaviours {
    public static void register() {
        BlockSpoutingBehaviour.BY_BLOCK.register(CPBlocks.PAPERMAKING_DEPOT.get(), new PulpSpoutingBehaviour());
    }
}
