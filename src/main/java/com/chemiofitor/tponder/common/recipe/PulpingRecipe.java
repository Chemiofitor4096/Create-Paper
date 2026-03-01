package com.chemiofitor.tponder.common.recipe;

import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.simibubi.create.content.processing.basin.BasinRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;

public class PulpingRecipe extends BasinRecipe {
    public PulpingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(CPRecipeTypes.PULPING, params);
    }
}
