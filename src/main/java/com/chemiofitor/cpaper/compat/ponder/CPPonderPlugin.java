package com.chemiofitor.cpaper.compat.ponder;

import com.chemiofitor.cpaper.CreatePaper;
import com.chemiofitor.cpaper.index.CPPonderScenes;
import com.chemiofitor.cpaper.index.CPPonderTags;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CPPonderPlugin implements PonderPlugin {
    @Override
    public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
        CPPonderScenes.register(helper);
    }

    @Override
    public void registerTags(@NotNull PonderTagRegistrationHelper<ResourceLocation> helper) {
        CPPonderTags.register(helper);
    }

    @Override
    public String getModId() {
        return CreatePaper.MOD_ID;
    }
}
