package com.chemiofitor.tponder.index;

import com.chemiofitor.tponder.client.scene.PapermakingScene;
import com.chemiofitor.tponder.client.scene.PulperScene;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CPPonderScenes {
    public static void register(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(CPBlocks.MECHANICAL_PULPER.getId())
                .addStoryBoard("mechanical_pulper/pulping", PulperScene::pulping);

        helper.forComponents(CPBlocks.PAPERMAKING_DEPOT.getId())
                .addStoryBoard("papermaking_depot/filling", PapermakingScene::filling);
    }
}
