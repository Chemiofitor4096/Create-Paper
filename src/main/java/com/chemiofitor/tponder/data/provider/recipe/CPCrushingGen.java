package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.api.data.recipe.CrushingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;

public final class CPCrushingGen extends CrushingRecipeGen {
    public CPCrushingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("wood_fiber", b -> b.output(CPItems.WOOD_FIBER, 4)
                .output(0.5f, CPItems.WOOD_FIBER, 2)
                .duration(240)
                .require(ItemTags.LOGS_THAT_BURN));
    }
}
