package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPBlocks;
import com.chemiofitor.tponder.index.CPItems;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.google.common.collect.Lists;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

import java.util.function.Consumer;

public final class CPRecipeGen extends RecipeProvider {
    public CPRecipeGen(PackOutput output) {
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
                .addRecipe(c -> ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CPBlocks.PAPERMAKING_FRAME)
                        .pattern(" S ")
                        .pattern("SFS")
                        .pattern(" S ")
                        .define('S', Items.STICK)
                        .define('F', ModItems.CANVAS.get())
                        .unlockedBy("has_canvas", has(ModItems.CANVAS.get()))
                        .save(c))
                .build(consumer, CreatePaper.asResource("crafting/papermaking_frame_from_canvas"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CPBlocks.PAPERMAKING_FRAME)
                .pattern("SFS")
                .pattern("FFF")
                .pattern("SFS")
                .define('S', Items.STICK)
                .define('F', Items.STRING)
                .unlockedBy("has_string", has(Items.STRING))
                .save(consumer, CreatePaper.asResource("crafting/papermaking_frame_from_string"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CPBlocks.MECHANICAL_PULPER)
                .pattern(" S ")
                .pattern(" F ")
                .pattern("DDD")
                .define('S', AllBlocks.COGWHEEL)
                .define('F', AllBlocks.ANDESITE_CASING)
                .define('D', AllItems.PROPELLER)
                .unlockedBy("has_propeller", has( AllItems.PROPELLER))
                .save(consumer, CreatePaper.asResource("crafting/mechical_pulper"));

        SimpleCookingRecipeBuilder.smoking(
                Ingredient.of(CPItems.WET_PAPER),
                RecipeCategory.MISC,
                CPItems.RAW_PAPER.get(),
                0f,
                100)
                .unlockedBy("has_raw_paper", has(CPItems.RAW_PAPER))
                .save(consumer, CreatePaper.asResource("smoking/raw_paper"));

        dyedPaper(Items.BLACK_DYE, CPItems.BLACK_PAPER, consumer);
        dyedPaper(Items.CYAN_DYE, CPItems.CYAN_PAPER, consumer);
        dyedPaper(Items.GRAY_DYE, CPItems.GRAY_PAPER, consumer);
        dyedPaper(Items.GREEN_DYE, CPItems.GREEN_PAPER, consumer);
        dyedPaper(Items.LIGHT_BLUE_DYE, CPItems.LIGHT_BLUE_PAPER, consumer);
        dyedPaper(Items.LIGHT_GRAY_DYE, CPItems.LIGHT_GRAY_PAPER, consumer);
        dyedPaper(Items.LIME_DYE, CPItems.LIME_PAPER, consumer);
        dyedPaper(Items.ORANGE_DYE, CPItems.ORANGE_PAPER, consumer);
        dyedPaper(Items.PINK_DYE, CPItems.PINK_PAPER, consumer);
        dyedPaper(Items.PURPLE_DYE, CPItems.PURPLE_PAPER, consumer);
        dyedPaper(Items.RED_DYE, CPItems.RED_PAPER, consumer);
        dyedPaper(Items.YELLOW_DYE, CPItems.YELLOW_PAPER, consumer);
        dyedPaper(Items.MAGENTA_DYE, CPItems.MAGENTA_PAPER, consumer);
        dyedPaper(Items.BLUE_DYE, CPItems.BLUE_PAPER, consumer);
        dyedPaper(Items.BROWN_DYE, CPItems.BROWN_PAPER, consumer);

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(FarmersDelight.MODID))
                .addRecipe(c -> {
                    CookingPotRecipeBuilder.cookingPotRecipe(AllItems.PULP.get(), 1, 200, 0)
                            .addIngredient(AllTags.AllItemTags.PULPIFIABLE.tag)
                            .addIngredient(AllTags.AllItemTags.PULPIFIABLE.tag)
                            .addIngredient(CPTagKeys.Items.ALKALINE)
                            .unlockedByAnyIngredient(AllItems.PULP.get())
                            .build(c);
                })
                .build(consumer, CreatePaper.asResource("cooking/pulp"));

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition(FarmersDelight.MODID))
                .addRecipe(c -> {
                    Ingredient woodDust = Ingredient.of(CPTagKeys.Items.WOOD_DUST);
                    Ingredient wood = Ingredient.merge(Lists.newArrayList(woodDust));
                    CookingPotRecipeBuilder.cookingPotRecipe(AllItems.PULP.get(), 1, 200, 0)
                            .addIngredient(wood)
                            .addIngredient(wood)
                            .addIngredient(wood)
                            .addIngredient(wood)
                            .addIngredient(CPTagKeys.Items.ALKALINE)
                            .unlockedByAnyIngredient(AllItems.PULP.get())
                            .build(c);
                })
                .build(consumer, CreatePaper.asResource("cooking/pulp_from_wood"));
    }

    private void dyedPaper(ItemLike dye, ItemEntry<?> paper, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, paper, 8)
                .pattern("XXX")
                .pattern("XSX")
                .pattern("XXX")
                .define('X', Items.PAPER)
                .define('S', dye)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(consumer, CreatePaper.asResource("crafting/" + paper.getId().getPath()));

    }
}
