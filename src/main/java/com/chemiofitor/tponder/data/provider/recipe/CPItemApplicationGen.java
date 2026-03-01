package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.data.recipe.ItemApplicationRecipeGen;
import net.minecraft.data.PackOutput;

public final class CPItemApplicationGen extends ItemApplicationRecipeGen {
    public CPItemApplicationGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        create("papermaking_depot", b -> b.output(CPBlocks.PAPERMAKING_DEPOT)
                .require(AllBlocks.DEPOT)
                .require(CPBlocks.PAPERMAKING_FRAME));
    }
}
