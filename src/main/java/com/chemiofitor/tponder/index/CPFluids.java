package com.chemiofitor.tponder.index;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import static com.chemiofitor.tponder.CreatePaper.REGISTRATE;

public final class CPFluids {

    public static final FluidEntry<ForgeFlowingFluid.Flowing> PULP = REGISTRATE.standardFluid("pulp")
            .register();


    public static void register() {}
}
