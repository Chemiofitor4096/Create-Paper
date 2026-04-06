package com.chemiofitor.tponder.data.provider;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPSoundEvents;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import net.createmod.ponder.foundation.PonderIndex;

public class CPLang {
    public static void addTranslations(RegistrateLangProvider provider) {
        provider.add("itemGroup.cpaper.main", "Create: Paper");

        provider.add("tag.item.forge.alkaline", "Alkaline");

        provider.add("block.cpaper.papermaking_frame.tooltip.behaviour1", "_Makes paper_ and _consumes 1/3 pulp_.");
        provider.add("block.cpaper.papermaking_frame.tooltip.condition1", "Right Click on Pulp Cauldron");
        provider.add("block.cpaper.papermaking_frame.tooltip.summary", "A frame for _making paper_, with a _filter screen_ in the center.");

        provider.add("tooltip.cpaper.papermaking.pressing", "This pressing can only use Papermaking Frame.");

        provider.add("create.recipe.pulping", "Pulping");
        provider.add("create.recipe.paper_making", "Paper Making");

        provider.add("tooltip.paper.process", "Processing Type: ");

        CPSoundEvents.provideLang(provider::add);
        PonderIndex.getLangAccess().provideLang(CreatePaper.MOD_ID, provider::add);
    }
}
