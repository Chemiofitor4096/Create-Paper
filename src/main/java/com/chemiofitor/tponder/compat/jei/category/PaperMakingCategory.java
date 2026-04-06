package com.chemiofitor.tponder.compat.jei.category;

import com.chemiofitor.tponder.common.recipe.PaperMakingRecipe;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.compat.jei.category.animations.AnimatedSpout;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;

public class PaperMakingCategory extends CreateRecipeCategory<PaperMakingRecipe> {
    private final AnimatedSpout spout = new AnimatedSpout();

    public PaperMakingCategory(Info<PaperMakingRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PaperMakingRecipe recipe, IFocusGroup focuses) {
        addFluidSlot(builder, 27, 51, recipe.getRequiredFluid());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 51)
                .setBackground(getRenderedSlot(), -1, -1)
                .addItemStack(getResultItem(recipe));
    }

    @Override
    public void draw(PaperMakingRecipe recipe, IRecipeSlotsView iRecipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        AllGuiTextures.JEI_SHADOW.render(graphics, 62, 57);
        AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 126, 29);
        spout.withFluids(recipe.getRequiredFluid()
                        .getMatchingFluidStacks())
                .draw(graphics, getBackground().getWidth() / 2 - 13, 22);
    }
}
