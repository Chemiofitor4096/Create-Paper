package com.chemiofitor.cpaper;

import com.chemiofitor.cpaper.compat.ponder.CPPonderPlugin;
import com.chemiofitor.cpaper.index.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CreatePaper.MOD_ID)
public class CreatePaper {
    public static final String MOD_ID = "cpaper";
    public static final String NAME = "Create: Paper";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    static {
        REGISTRATE.setCreativeTab(CPCreativeModeTabs.MAIN_TAB);
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                        .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
        );
    }

    public CreatePaper(FMLJavaModLoadingContext context) {
        final IEventBus bus = context.getModEventBus();

        CPSoundEvents.prepare();

        CPCreativeModeTabs.register(bus);

        REGISTRATE.registerEventListeners(bus);

        CPFluids.register();
        CPBlocks.register();

        CPItems.register();
        CPRecipeTypes.register(bus);

        CPBlockEntityTypes.register();

        bus.addListener(CreatePaper::client);
        bus.addListener(CreatePaper::init);
        bus.addListener(CPSoundEvents::register);

        LOGGER.info("Create: Paper has loaded!.");
    }

    private static void client(final FMLClientSetupEvent event) {
        CPPartialModels.init();

        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new CPPonderPlugin());
        });
    }

    private static void init(final FMLCommonSetupEvent event) {
        CPFluids.registerFluidInteractions();

        event.enqueueWork(CPBlockSpoutingBehaviours::register);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
