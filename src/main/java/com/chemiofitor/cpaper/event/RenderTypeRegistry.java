package com.chemiofitor.cpaper.event;

import com.chemiofitor.cpaper.CreatePaper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CreatePaper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RenderTypeRegistry {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }
}
