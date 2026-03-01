package com.chemiofitor.tponder.data.accessor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.Map;

public interface RecipesGetter {
    Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> cpaper$recipes();
}
