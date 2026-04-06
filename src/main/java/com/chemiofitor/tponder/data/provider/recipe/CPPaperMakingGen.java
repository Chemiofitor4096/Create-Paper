package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPItems;
import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public class CPPaperMakingGen extends ProcessingRecipeGen {
    public CPPaperMakingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("paper", b -> b.require(CPFluids.FINE_PULP.get(), 250)
                .output(Items.PAPER));
        create("xuan_paper", b -> b.require(CPFluids.XUAN_PAPER_PULP.get(), 250)
                .output(CPItems.XUAN_PAPER));
        create("raw_paper", b -> b.require(CPFluids.PULP.get(), 250)
                .output(CPItems.WET_PAPER));
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return CPRecipeTypes.PAPER_MAKING;
    }
}
