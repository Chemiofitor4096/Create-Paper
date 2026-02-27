package com.chemiofitor.tponder.data.provider.tag;

import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPTagKeys;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Function;

public final class CPFluidTagGen extends CPTagGen<Fluid> {
    public CPFluidTagGen(RegistrateTagsProvider.IntrinsicImpl<Fluid> provIn) {
        super(provIn, fluid -> fluid.builtInRegistryHolder().key());
    }
}
