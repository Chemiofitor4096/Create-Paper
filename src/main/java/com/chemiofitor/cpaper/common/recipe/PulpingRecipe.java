package com.chemiofitor.cpaper.common.recipe;

import com.chemiofitor.cpaper.index.CPRecipeTypes;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;

public class PulpingRecipe extends BasinRecipe {
    public PulpingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(CPRecipeTypes.PULPING, params);
    }
}
