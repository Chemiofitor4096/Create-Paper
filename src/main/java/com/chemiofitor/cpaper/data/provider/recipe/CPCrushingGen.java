package com.chemiofitor.cpaper.data.provider.recipe;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPBlocks;
import com.chemiofitor.cpaper.index.CPItems;
import com.jesz.createdieselgenerators.CreateDieselGenerators;
import com.simibubi.create.api.data.recipe.CrushingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

public final class CPCrushingGen extends CrushingRecipeGen {
    public CPCrushingGen(PackOutput generator) {
        super(generator, CreatePaper.MOD_ID);

        create("wood_fiber", b -> b.output(CPItems.WOOD_FIBER, 4)
                .output(0.5f, CPItems.WOOD_FIBER, 2)
                .duration(240)
                .require(ItemTags.LOGS_THAT_BURN)
                .withCondition(new NotCondition(new ModLoadedCondition(CreateDieselGenerators.ID))));

        create("coal_gangue_recycling",b -> b.output(0.1f, Items.COAL)
                .duration(120)
                .require(CPBlocks.COAL_GANGUE.get()));
    }
}
