package com.chemiofitor.cpaper.event;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPTagKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreatePaper.MOD_ID, value = Dist.CLIENT)
public class TooltipEvent {
    @SubscribeEvent
    public static void addTip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        var tips = event.getToolTip();
        if (stack.is(CPTagKeys.Items.PAPER)) {
            CompoundTag tag =stack.getOrCreateTag();
            if (tag.contains("type")) {
                String type = tag.getString("type");
                tips.add(Component.translatable("tooltip.paper.process")
                        .withStyle(ChatFormatting.GRAY)
                        .append(
                                Component.translatable("tooltip.paper.type." + type)
                                        .withStyle(ChatFormatting.GOLD)
                        ));
            }
        }
    }
}
