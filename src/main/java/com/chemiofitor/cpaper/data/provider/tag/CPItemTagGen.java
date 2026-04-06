package com.chemiofitor.cpaper.data.provider.tag;

import com.chemiofitor.cpaper.index.CPItems;
import com.chemiofitor.cpaper.index.CPTagKeys;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import vectorwing.farmersdelight.common.registry.ModItems;

public final class CPItemTagGen extends CPTagGen<Item> {
    @SuppressWarnings("deprecation")
    public CPItemTagGen(RegistrateTagsProvider.IntrinsicImpl<Item> provIn) {
        super(provIn, item -> item.builtInRegistryHolder().key());

        prov.tag(CPTagKeys.Items.PULPIFIABLE)
                .add(ModItems.STRAW.get())
                .add(ModItems.TREE_BARK.get())
                .add(CPItems.WOOD_FIBER.get())
                .add(CPItems.WASTE_PAPER.get());

        prov.tag(CPTagKeys.Items.ALKALINE)
                .add(Items.BONE_MEAL);

        prov.tag(CPTagKeys.Items.PAPER)
                .add(Items.PAPER)
                .add(CPItems.RAW_PAPER.get())
                .addTag(CPTagKeys.Items.COLOR_PAPER);
    }
}
