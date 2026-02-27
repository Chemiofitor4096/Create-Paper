package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

public final class CPCompactingGen extends CompactingRecipeGen {
    public CPCompactingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("raw_paper", b -> b.output(0.5f, CPItems.RAW_PAPER)
                .output(0.5f, CPItems.WASTE_PAPER)
                .output(Fluids.WATER, 250)
                .require(AllItems.PULP)
                .require(AllItems.PULP));

        create("cardboard", b -> b.output(0.5f, AllItems.CARDBOARD)
                .output(0.5f, CPItems.WASTE_PAPER, 3)
                .require(CPItems.RAW_PAPER)
                .require(CPItems.RAW_PAPER)
                .require(CPItems.RAW_PAPER));
    }
}
