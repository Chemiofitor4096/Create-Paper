package com.chemiofitor.cpaper.data.gen;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.compat.ponder.CPPonderPlugin;
import com.chemiofitor.cpaper.data.provider.CPLang;
import com.chemiofitor.cpaper.data.provider.recipe.*;
import com.chemiofitor.cpaper.data.provider.tag.CPItemTagGen;
import com.chemiofitor.cpaper.index.CPSoundEvents;
import com.tterrag.registrate.providers.ProviderType;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.chemiofitor.cpaper.CreatePaper.REGISTRATE;

@Mod.EventBusSubscriber(modid = CreatePaper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CPDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        PonderIndex.addPlugin(new CPPonderPlugin());
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
        generator.addProvider(run, new CPBulkFermentingGen(output));
        generator.addProvider(run, new CPBasinFermentingGen(output));
        generator.addProvider(run, new CPPaperMakingGen(output));

        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CPItemTagGen::new);

        REGISTRATE.addDataGenerator(ProviderType.LANG, CPLang::addTranslations);
    }
}
