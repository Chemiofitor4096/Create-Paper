package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.jesz.createdieselgenerators.CDGItems;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

public final class CPMixingGen extends MixingRecipeGen {
    public CPMixingGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        create("pulp_from_chips", b -> b.output(AllItems.PULP)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
                .require(CDGItems.WOOD_CHIPS)
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
    }
}
