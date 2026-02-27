package com.chemiofitor.tponder.event;

import com.chemiofitor.tponder.index.CPBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class TooltipEvent {
    @SubscribeEvent
    public static void add(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        var list = event.getToolTip();
        if (CPBlocks.PAPERMAKING_FRAME.isIn(stack) && stack.getOrCreateTag().getBoolean("IsPressing")) {
            list.add(Component.empty());
            list.add(Component.translatable("tooltip.cpaper.papermaking.pressing").withStyle(ChatFormatting.GREEN));
        }
    }
}
