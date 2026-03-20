package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPItems;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.google.common.collect.Lists;
import com.jesz.createdieselgenerators.CDGFluids;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

public final class CPMixingGen extends MixingRecipeGen {
    public CPMixingGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        Ingredient woodDust = Ingredient.of(CPTagKeys.Items.WOOD_DUST);
        Ingredient wood = Ingredient.merge(Lists.newArrayList(woodDust));

        create("pulp_from_wood", b -> b.output(AllItems.PULP)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 250));

        create("pulp", b -> b.output(AllItems.PULP)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 250));

        create("pulp_fluid", b -> b.output(CPFluids.PULP.get(), 1000)
                .require(AllItems.PULP)
                .require(AllItems.PULP)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 1000));

        create("oilpaper", b -> b.output(CPItems.OILPAPER)
                .output(CDGFluids.PLANT_OIL.get(), 100)
                .require(Items.PAPER)
                .require(CDGFluids.PLANT_OIL.get(), 100)
                .withCondition(new ModLoadedCondition(CreateDieselGenerators.ID)));
    }
}
