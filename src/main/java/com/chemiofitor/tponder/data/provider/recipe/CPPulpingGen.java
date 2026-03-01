package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.simibubi.create.AllTags;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;

public class CPPulpingGen extends ProcessingRecipeGen {
    public CPPulpingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("pulp_alkaline", b -> b.output(CPFluids.PULP.get(), 1000)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(AllTags.AllItemTags.PULPIFIABLE.tag)
                .require(CPTagKeys.Items.ALKALINE)
                .require(CPTagKeys.Items.ALKALINE)
                .require(Fluids.WATER, 1000));
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return CPRecipeTypes.PULPING;
    }
}
