package com.chemiofitor.tponder.common.recipe;

import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class PaperMakingRecipe extends ProcessingRecipe<RecipeWrapper> {
    public PaperMakingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(CPRecipeTypes.PAPER_MAKING, params);
    }

    @Override
    protected int getMaxInputCount() {
        return 0;
    }

    @Override
    protected int getMaxOutputCount() {
        return 1;
    }

    @Override
    protected int getMaxFluidInputCount() {
        return 1;
    }

    public FluidIngredient getRequiredFluid() {
        if (fluidIngredients.isEmpty())
            throw new IllegalStateException("Filling Recipe: " + id.toString() + " has no fluid ingredient!");
        return fluidIngredients.get(0);
    }

    @Override
    public boolean matches(RecipeWrapper recipeWrapper, Level level) {
        return true;
    }
}
