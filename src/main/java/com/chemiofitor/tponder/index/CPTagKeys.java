package com.chemiofitor.tponder.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class CPTagKeys {

    public static final class Items {
        public static final TagKey<Item> PULPIFIABLE = create("create", "pulpifiable");

        private static TagKey<Item> create(String namespace, String id) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, id));
        }
    }

    public static final class Blocks {

        private static TagKey<Block> create(String namespace, String id) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, id));
        }
    }

    public static final class Fluids {

        private static TagKey<Fluid> create(String namespace, String id) {
            return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(namespace, id));
        }
    }
}
