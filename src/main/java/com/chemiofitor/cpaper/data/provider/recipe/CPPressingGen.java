package com.chemiofitor.cpaper.data.provider.recipe;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPItems;
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
