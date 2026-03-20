package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.CreatePaper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CPPonderTags {
    public static void register(@NotNull PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.registerTag(CreatePaper.asResource("papermaking"))
                .addToIndex()
                .item(CPItems.RAW_PAPER.get(), true, false)
                .title("Papermaking")
                .description("Components used for making paper")
                .register();
    }
}
