package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

public final class CPItems {
    static {
        REGISTRATE.setCreativeTab(CPCreativeModeTabs.MAIN_TAB);
    }

    public static final ItemEntry<Item> WOOD_FIBER = REGISTRATE.item("wood_fiber", Item::new)
            .register();

    public static final ItemEntry<Item> RAW_PAPER = REGISTRATE.item("raw_paper", Item::new)
            .register();

    public static final ItemEntry<Item> WASTE_PAPER = REGISTRATE.item("waste_paper", Item::new)
            .register();

    public static final ItemEntry<Item> PAPERMAKING_FRAME = REGISTRATE.item("papermaking_frame", Item::new)
            .model(AssetLookup.existingItemModel())
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Items initialized");
    }
}
