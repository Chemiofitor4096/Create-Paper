package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;

public final class CPSplashingGen extends ProcessingRecipeGen {
    public CPSplashingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("wet_paper", b -> b.output(CPItems.WET_PAPER).require(CPItems.RAW_PAPER));
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.SPLASHING;
    }
}
