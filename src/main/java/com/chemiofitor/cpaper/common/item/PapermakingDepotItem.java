package com.chemiofitor.cpaper.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PapermakingDepotItem extends BlockItem {
    public PapermakingDepotItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        if (stack.getOrCreateTag().getBoolean("IsPressing")) {
            list.add(Component.translatable("tooltip.cpaper.papermaking.pressing").withStyle(ChatFormatting.GREEN));
        }
    }
}
