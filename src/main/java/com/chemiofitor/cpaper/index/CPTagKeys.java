package com.chemiofitor.cpaper.index;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class CPTagKeys {

    public static final class Items {
        public static final TagKey<Item> PULPIFIABLE = create("create", "pulpifiable");

        public static final TagKey<Item> COLOR_PAPER = create("cpaper", "color_paper");

        public static final TagKey<Item> PAPER = create("cpaper", "paper");

        public static final TagKey<Item> ALKALINE = create("forge", "alkaline");

        public static final TagKey<Item> WOOD_DUST = create("forge", "dusts/wood");

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
        public static final  TagKey<Fluid> PLANTOIL = create("forge", "plantoil");

        private static TagKey<Fluid> create(String namespace, String id) {
            return TagKey.create(Registries.FLUID, ResourceLocation.fromNamespaceAndPath(namespace, id));
        }
    }
}
