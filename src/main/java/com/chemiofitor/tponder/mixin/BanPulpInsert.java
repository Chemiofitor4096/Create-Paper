package com.chemiofitor.tponder.mixin;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.logistics.depot.DepotBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DepotBehaviour.class)
public class BanPulpInsert {

    @Inject(
            remap = false,
            method = "<init>",
            at = @At("TAIL")
    )
    public void init(SmartBlockEntity be, CallbackInfo ci) {
        DepotBehaviour behaviour =  (DepotBehaviour) (Object) this;
        behaviour.onlyAccepts(stack -> !stack.is(AllItems.PULP.get()));
    }
}
