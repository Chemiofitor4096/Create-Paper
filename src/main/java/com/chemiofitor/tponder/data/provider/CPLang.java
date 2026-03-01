package com.chemiofitor.tponder.data.provider;

import com.chemiofitor.tponder.index.CPSoundEvents;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class CPLang {
    public static void addTranslations(RegistrateLangProvider provider) {
        provider.add("itemGroup.cpaper.main", "Create: Paper");

        provider.add("tag.item.forge.alkaline", "Alkaline");

        provider.add("tooltip.cpaper.papermaking.pressing", "This pressing can only use Papermaking Frame.");

        provider.add("create.recipe.pulping", "Pulping");

        CPSoundEvents.provideLang(provider::add);
    }
}
