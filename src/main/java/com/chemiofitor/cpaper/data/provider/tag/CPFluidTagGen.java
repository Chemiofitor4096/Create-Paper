package com.chemiofitor.cpaper.data.provider.tag;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.level.material.Fluid;

public final class CPFluidTagGen extends CPTagGen<Fluid> {
    public CPFluidTagGen(RegistrateTagsProvider.IntrinsicImpl<Fluid> provIn) {
        super(provIn, fluid -> fluid.builtInRegistryHolder().key());
    }
}
