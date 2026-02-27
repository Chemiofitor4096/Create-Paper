package com.chemiofitor.tponder.data;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RemovedRecipes {
    public static final List<ResourceLocation> LIST = new ArrayList<>();

    static {
        LIST.add(ResourceLocation.fromNamespaceAndPath("minecraft", "paper"));

        LIST.add(ResourceLocation.fromNamespaceAndPath("create", "pressing/sugar_cane"));
        LIST.add(ResourceLocation.fromNamespaceAndPath("create", "pressing/cardboard"));

        LIST.add(ResourceLocation.fromNamespaceAndPath("create", "crafting/materials/red_sand_paper"));
        LIST.add(ResourceLocation.fromNamespaceAndPath("create", "crafting/materials/sand_paper"));

        LIST.add(ResourceLocation.fromNamespaceAndPath("farmersdelight", "paper_from_tree_bark"));
    }
}
