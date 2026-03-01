package com.chemiofitor.tponder.mixin;

import com.chemiofitor.tponder.index.CPPartialModels;
import com.simibubi.create.AllPartialModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AllPartialModels.class)
public class PartialModelsLoad {
    @Inject(
            remap = false,
            method = "init",
            at = @At(value = "HEAD")
    )
    private static void init(CallbackInfo ci) {
        CPPartialModels.init();
    }
}
