package com.chemiofitor.tponder.compat.jei.decorator;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPBlocks;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryDecorator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class PressingRecipeDecorator implements IRecipeCategoryDecorator<PressingRecipe> {
    @Override
    public void draw(PressingRecipe recipe, IRecipeCategory<PressingRecipe> recipeCategory, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        if (recipe.getId().equals(CreatePaper.asResource("pressing/raw_paper"))) {
            AllGuiTextures.JEI_SLOT.render(guiGraphics, 26, 20);
            ItemStack stack = CPBlocks.PAPERMAKING_DEPOT.asStack();
            stack.getOrCreateTag().putBoolean("IsPressing", true);

            guiGraphics.renderItem(stack, 27, 21);

            if (26 < mouseX && mouseX < 46 && 20 < mouseY && mouseY < 40)
                guiGraphics.renderTooltip(Minecraft.getInstance().font, stack, (int) mouseX, (int) mouseY);
        }
    }
}
