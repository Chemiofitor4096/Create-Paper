package com.chemiofitor.tponder.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

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

        LIST.add(ResourceLocation.fromNamespaceAndPath("create", "mixing/cardboard_pulp"));

        LIST.add(ResourceLocation.fromNamespaceAndPath("farmersdelight", "paper_from_tree_bark"));

        if (ModList.get().isLoaded("createdieselgenerators")) {
            LIST.add(ResourceLocation.fromNamespaceAndPath("cpaper", "crushing/wood_fiber"));

            LIST.add(ResourceLocation.fromNamespaceAndPath("createdieselgenerators", "bulk_fermenting/pulp"));
        }
    }
}
