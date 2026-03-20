package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

public final class CPItems {

    public static final ItemEntry<Item> PINK_PAPER = REGISTRATE.item("pink_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> RED_PAPER = REGISTRATE.item("red_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> ORANGE_PAPER = REGISTRATE.item("orange_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> YELLOW_PAPER = REGISTRATE.item("yellow_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> LIME_PAPER = REGISTRATE.item("lime_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> GREEN_PAPER = REGISTRATE.item("green_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> LIGHT_BLUE_PAPER = REGISTRATE.item("light_blue_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> CYAN_PAPER = REGISTRATE.item("cyan_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> BLUE_PAPER = REGISTRATE.item("blue_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> PURPLE_PAPER = REGISTRATE.item("purple_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> MAGENTA_PAPER = REGISTRATE.item("magenta_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> BLACK_PAPER = REGISTRATE.item("black_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> GRAY_PAPER = REGISTRATE.item("gray_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> LIGHT_GRAY_PAPER = REGISTRATE.item("light_gray_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> BROWN_PAPER = REGISTRATE.item("brown_paper", Item::new)
            .tag(CPTagKeys.Items.COLOR_PAPER)
            .register();

    public static final ItemEntry<Item> WOOD_FIBER = REGISTRATE.item("wood_fiber", Item::new)
            .register();

    public static final ItemEntry<Item> RAW_PAPER = REGISTRATE.item("raw_paper", Item::new)
            .register();

    public static final ItemEntry<Item> WET_PAPER = REGISTRATE.item("wet_paper", Item::new)
            .lang("Wet Raw Paper")
            .model(AssetLookup.existingItemModel())
            .register();

    public static final ItemEntry<Item> OILPAPER = REGISTRATE.item("oilpaper", Item::new)
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();

    public static final ItemEntry<Item> WASTE_PAPER = REGISTRATE.item("waste_paper", Item::new)
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Items initialized");
    }
}
