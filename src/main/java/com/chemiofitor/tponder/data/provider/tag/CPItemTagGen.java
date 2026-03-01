package com.chemiofitor.tponder.data.provider.tag;

import com.chemiofitor.tponder.index.CPItems;
import com.chemiofitor.tponder.index.CPTagKeys;
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
    }
}
