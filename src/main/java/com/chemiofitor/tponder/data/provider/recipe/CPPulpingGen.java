package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.google.common.collect.Lists;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

public class CPPulpingGen extends ProcessingRecipeGen {
    public CPPulpingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("pulp", b -> b.output(CPFluids.PULP.get(), 1000)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 1000));

        Ingredient woodDust = Ingredient.of(CPTagKeys.Items.WOOD_DUST);
        Ingredient wood = Ingredient.merge(Lists.newArrayList(woodDust));

        create("pulp_from_wood", b -> b.output(CPFluids.PULP.get(), 1000)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(wood)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 1000));

        create("fine_pulp", b -> b.output(CPFluids.FINE_PULP.get(), 500)
                .require(CPFluids.PULP.get(), 500)
                .require(CPTagKeys.Items.ALKALINE));
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return CPRecipeTypes.PULPING;
    }
}
