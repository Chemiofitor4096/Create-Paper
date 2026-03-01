package com.chemiofitor.tponder.data.gen;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.data.provider.CPLang;
import com.chemiofitor.tponder.data.provider.recipe.*;
import com.chemiofitor.tponder.data.provider.tag.CPItemTagGen;
import com.chemiofitor.tponder.index.CPSoundEvents;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

@Mod.EventBusSubscriber(modid = CreatePaper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CPDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        boolean run = event.includeClient();

        generator.addProvider(run, CPSoundEvents.provider(generator));

        generator.addProvider(run, new CPCrushingGen(output));
        generator.addProvider(run, new CPMixingGen(output));
        generator.addProvider(run, new CPRecipeGen(output));
        generator.addProvider(run, new CPCompactingGen(output));
        generator.addProvider(run, new CPPressingGen(output));
        generator.addProvider(run, new CPItemApplicationGen(output));
        generator.addProvider(run, new CPSplashingGen(output));
        generator.addProvider(run, new CPPulpingGen(output));

        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CPItemTagGen::new);

        REGISTRATE.addDataGenerator(ProviderType.LANG, CPLang::addTranslations);

    }
}
