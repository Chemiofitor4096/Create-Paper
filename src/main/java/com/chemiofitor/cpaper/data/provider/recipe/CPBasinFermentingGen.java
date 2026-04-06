package com.chemiofitor.cpaper.data.provider.recipe;

import com.chemiofitor.cpaper.CreatePaper;
import com.jesz.createdieselgenerators.CDGRecipes;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;

public final class CPBasinFermentingGen extends ProcessingRecipeGen {
    public CPBasinFermentingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return CDGRecipes.BASIN_FERMENTING;
    }
}
