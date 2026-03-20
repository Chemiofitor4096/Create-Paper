package com.chemiofitor.tponder.data.provider.recipe;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPItems;
import com.jesz.createdieselgenerators.CDGFluids;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;

public class CPFillingGen extends FillingRecipeGen {
    public CPFillingGen(PackOutput output) {
        super(output, CreatePaper.MOD_ID);

        create("oilpaper", b -> b.output(CPItems.OILPAPER)
                .require(Items.PAPER)
                .require(CDGFluids.PLANT_OIL.get(), 1)
                .withCondition(new ModLoadedCondition(CreateDieselGenerators.ID)));
    }
}
