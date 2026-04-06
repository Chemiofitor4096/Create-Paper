package com.chemiofitor.cpaper.data.provider.recipe;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

public final class CPCompactingGen extends CompactingRecipeGen {
    public CPCompactingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("raw_paper", b -> b.output(0.75f, CPItems.RAW_PAPER)
                .output(0.25f, CPItems.WASTE_PAPER)
                .output(Fluids.WATER, 250)
                .require(AllItems.PULP)
                .require(AllItems.PULP));

        create("cardboard_from_raw_paper", b -> b.output(0.75f, AllItems.CARDBOARD)
                .output(0.25f, CPItems.WASTE_PAPER, 2)
                .require(CPItems.RAW_PAPER)
                .require(CPItems.RAW_PAPER));

        create("cardboard_from_wet_paper", b -> b.output(AllItems.CARDBOARD)
                .require(CPItems.WET_PAPER)
                .require(CPItems.WET_PAPER));
    }
}
