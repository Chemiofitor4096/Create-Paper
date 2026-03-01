package com.chemiofitor.tponder.data.provider;

import com.chemiofitor.tponder.CreatePaper;
import com.chemiofitor.tponder.index.CPConfiguredFeatures;
import com.simibubi.create.Create;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CPGeneratedEntriesProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, CPConfiguredFeatures::bootstrap);

    public CPGeneratedEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CreatePaper.MOD_ID));
    }

    @Override
    public String getName() {
        return "Create's Generated Registry Entries";
    }
}
