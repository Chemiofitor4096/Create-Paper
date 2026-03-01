package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.common.block.MechanicalPulperBlock;
import com.chemiofitor.tponder.common.block.PapermakingDepotBlock;
import com.chemiofitor.tponder.common.block.PapermakingFrameBlock;
import com.chemiofitor.tponder.common.item.CombustibleBlockItem;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllDisplaySources;
import com.simibubi.create.AllMountedStorageTypes;
import com.simibubi.create.content.logistics.depot.MountedDepotInteractionBehaviour;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;
import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.interaction.MovingInteractionBehaviour.interactionBehaviour;
import static com.simibubi.create.api.contraption.storage.item.MountedItemStorageType.mountedItemStorage;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public final class CPBlocks {
    static {
        REGISTRATE.setCreativeTab(CPCreativeModeTabs.MAIN_TAB);
    }

    public static final BlockEntry<MechanicalPulperBlock> MECHANICAL_PULPER =
            REGISTRATE.block("mechanical_pulper", MechanicalPulperBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.STONE))
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                    .item(AssemblyOperatorBlockItem::new)
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<Block> COAL_GANGUE = REGISTRATE.block("coal_gangue", Block::new)
            .initialProperties(SharedProperties::stone)
            .transform(pickaxeOnly())
            .tag(BlockTags.DRIPSTONE_REPLACEABLE)
            .tag(BlockTags.AZALEA_ROOT_REPLACEABLE)
            .tag(BlockTags.MOSS_REPLACEABLE)
            .tag(BlockTags.LUSH_GROUND_REPLACEABLE)
            .item(CombustibleBlockItem::new)
            .onRegister(i -> i.setBurnTime(100))
            .build()
            .register();

    public static final BlockEntry<PapermakingFrameBlock> PAPERMAKING_FRAME = REGISTRATE.block("papermaking_frame", PapermakingFrameBlock::new)
            .initialProperties(SharedProperties::wooden)
            .properties(BlockBehaviour.Properties::instabreak)
            .blockstate((c, p) -> p.simpleBlock(c.get(), p.models().getExistingFile(p.modLoc("block/papermaking_frame"))))
            .transform(axeOrPickaxe())
            .item()
            .model(AssetLookup.existingItemModel())
            .build()
            .register();

    public static final BlockEntry<PapermakingDepotBlock> PAPERMAKING_DEPOT = REGISTRATE.block("papermaking_depot", PapermakingDepotBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_BROWN))
            .blockstate((c, p) -> p.simpleBlock(c.get(), p.models().getExistingFile(p.modLoc("block/papermaking_depot"))))
            .transform(axeOrPickaxe())
            .transform(displaySource(AllDisplaySources.ITEM_NAMES))
            .onRegister(interactionBehaviour(new MountedDepotInteractionBehaviour()))
            .transform(mountedItemStorage(AllMountedStorageTypes.DEPOT))
            .loot((p, b) -> p.add(b, p.createSingleItemTable(AllBlocks.DEPOT.get())
                    .withPool(p.applyExplosionCondition(PAPERMAKING_FRAME.get(), LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(PAPERMAKING_FRAME.get()))))))
            .item()
            .build()
            .register();

    public static void register() {
        CreatePaper.LOGGER.info("Blocks initialized");
    }
}
