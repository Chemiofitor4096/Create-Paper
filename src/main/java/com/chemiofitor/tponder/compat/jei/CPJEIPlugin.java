package com.chemiofitor.tponder.compat.jei;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.compat.jei.decorator.PressingRecipeDecorator;
import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IAdvancedRegistration;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

@JeiPlugin
public class CPJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return CreatePaper.asResource("main");
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();

        Optional<RecipeType<PressingRecipe>> pressingType = jeiHelpers.getRecipeType(
                Create.asResource("pressing"), PressingRecipe.class);

        pressingType.ifPresent(pressingRecipeRecipeType ->
                registration.addRecipeCategoryDecorator(pressingRecipeRecipeType, new PressingRecipeDecorator()));
    }
}
