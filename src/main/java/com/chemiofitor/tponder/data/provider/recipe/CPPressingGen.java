package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import net.minecraft.data.PackOutput;

public final class CPPressingGen extends PressingRecipeGen {
    public CPPressingGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        create("raw_paper", b -> b.output(CPItems.RAW_PAPER)
                .require(AllItems.PULP));
    }
}
