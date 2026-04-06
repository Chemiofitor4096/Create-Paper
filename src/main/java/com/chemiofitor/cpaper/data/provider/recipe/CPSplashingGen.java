package com.chemiofitor.cpaper.data.provider.recipe;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPItems;
import com.chemiofitor.cpaper.index.CPTagKeys;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.api.data.recipe.ProcessingRecipeGen;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public final class CPSplashingGen extends ProcessingRecipeGen {
    public CPSplashingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("wet_paper", b -> b.output(CPItems.WET_PAPER).require(CPItems.RAW_PAPER));

        create("paper", b -> b.output(Items.PAPER).require(CPTagKeys.Items.COLOR_PAPER));
    }

    @Override
    protected IRecipeTypeInfo getRecipeType() {
        return AllRecipeTypes.SPLASHING;
    }
}
