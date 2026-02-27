package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.AllItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

public final class CPCraftingGen extends RecipeProvider {
    public CPCraftingGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.SAND_PAPER)
                .requires(Items.SAND)
                .requires(CPItems.RAW_PAPER)
                .unlockedBy("has_raw_paper", has(CPItems.RAW_PAPER))
                .save(consumer, CreatePaper.asResource("crafting/sand_paper"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.RED_SAND_PAPER)
                .requires(Items.RED_SAND)
                .requires(CPItems.RAW_PAPER)
                .unlockedBy("has_raw_paper", has(CPItems.RAW_PAPER))
                .save(consumer, CreatePaper.asResource("crafting/red_sand_paper"));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(FarmersDelight.MODID))
                .addRecipe(c -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CPItems.PAPERMAKING_FRAME)
                        .pattern(" S ")
                        .pattern("SFS")
                        .pattern(" S ")
                        .define('S', Items.STICK)
                        .define('F', ModItems.CANVAS.get())
                        .unlockedBy("has_canvas", has(ModItems.CANVAS.get()))
                        .save(c))
                .build(consumer, CreatePaper.asResource("crafting/papermaking_frame_from_canvas"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CPItems.PAPERMAKING_FRAME)
                .pattern("SFS")
                .pattern("FFF")
                .pattern("SFS")
                .define('S', Items.STICK)
                .define('F', Items.STRING)
                .unlockedBy("has_string", has(Items.STRING))
                .save(consumer, CreatePaper.asResource("crafting/papermaking_frame_from_string"));
    }
}
