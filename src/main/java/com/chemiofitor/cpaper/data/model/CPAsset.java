package com.chemiofitor.cpaper.data.model;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.function.Function;

public class CPAsset {
    public static Function<BlockState, ModelFile> forCauldron(DataGenContext<?, ?> ctx, RegistrateBlockstateProvider prov, String path) {
        return state -> {
            int level = state.getValue(LayeredCauldronBlock.LEVEL);
            return prov.models().getExistingFile(prov.modLoc("block/" + path + "_" + (level == 3 ? "full" : "level" + level)));
        };
    }
}
