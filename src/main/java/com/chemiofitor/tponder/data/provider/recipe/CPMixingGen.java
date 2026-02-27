package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

public final class CPMixingGen extends MixingRecipeGen {
    public CPMixingGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        create("pulp_alkaline", b -> b.output(AllItems.PULP)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(Items.BONE_MEAL)
                .require(Fluids.WATER, 250));

    }
}
