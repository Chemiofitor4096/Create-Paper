package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.common.block.PapermakingFrameBlock;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllDisplaySources;
import com.simibubi.create.AllMountedStorageTypes;
import com.simibubi.create.content.logistics.depot.MountedDepotInteractionBehaviour;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;
import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.interactionBehaviour;
import static com.simibubi.create.api.contraption.storage.item.MountedItemStorageType.mountedItemStorage;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public final class CPBlocks {
    static {
        REGISTRATE.setCreativeTab(CPCreativeModeTabs.MAIN_TAB);
    }

    public static final BlockEntry<PapermakingFrameBlock> PAPERMAKING_FRAME = REGISTRATE.block("papermaking_depot", PapermakingFrameBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_BROWN))
            .blockstate((c, p) -> p.simpleBlock(c.get(), p.models().getExistingFile(p.modLoc("block/papermaking_depot"))))
            .transform(axeOrPickaxe())
            .transform(displaySource(AllDisplaySources.ITEM_NAMES))
            .onRegister(interactionBehaviour(new MountedDepotInteractionBehaviour()))
            .transform(mountedItemStorage(AllMountedStorageTypes.DEPOT))
            .loot((p, b) -> p.add(b, p.createSingleItemTable(AllBlocks.DEPOT.get())
                    .withPool(p.applyExplosionCondition(CPItems.PAPERMAKING_FRAME.get(), LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(CPItems.PAPERMAKING_FRAME.get()))))))
            .item()
            .build()
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Blocks initialized");
    }
}
