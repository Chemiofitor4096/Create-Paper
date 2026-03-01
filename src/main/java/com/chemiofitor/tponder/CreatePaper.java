package com.chemiofitor.tponder;

import com.chemiofitor.tponder.index.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        bus.addListener(CreatePaper::init);
        bus.addListener(CPSoundEvents::register);

        LOGGER.info("Create: Paper has loaded!.");
    }

    public static void init(final FMLCommonSetupEvent event) {
        CPFluids.registerFluidInteractions();

        event.enqueueWork(CPBlockSpoutingBehaviours::register);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
