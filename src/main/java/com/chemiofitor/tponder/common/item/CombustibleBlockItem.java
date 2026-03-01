package com.chemiofitor.tponder.common.item;

import lombok.Setter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

@Setter
public class CombustibleBlockItem extends BlockItem {
    private int burnTime = -1;

    public CombustibleBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return this.burnTime;
    }
}
