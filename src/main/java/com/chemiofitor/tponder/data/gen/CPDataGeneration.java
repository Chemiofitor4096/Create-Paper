package com.chemiofitor.tponder.data.gen;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.data.provider.recipe.*;
import com.chemiofitor.tponder.data.provider.tag.CPItemTagGen;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

@Mod.EventBusSubscriber(modid = CreatePaper.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CPDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        REGISTRATE.addRawLang("itemGroup.cpaper.main", "Create: Paper");
        REGISTRATE.addRawLang("tooltip.cpaper.papermaking.pressing", "This pressing can only use Papermaking Frame.");

        add(generator, CPCrushingGen::new);
        add(generator, CPMixingGen::new);
        add(generator, CPCraftingGen::new);
        add(generator, CPCompactingGen::new);
        add(generator, CPPressingGen::new);
        add(generator, CPItemApplicationGen::new);

        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CPItemTagGen::new);


    }

    private static void add(DataGenerator generator, Function<PackOutput, DataProvider> function) {
        generator.addProvider(true, function.apply(generator.getPackOutput()));
    }
}
