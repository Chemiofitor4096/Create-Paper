package com.chemiofitor.tponder.common.block.behavior;

import com.chemiofitor.tponder.common.block.entity.PapermakingDepotBlockEntity;
import com.chemiofitor.tponder.common.recipe.PaperFillingRecipe;
import com.chemiofitor.tponder.common.recipe.PaperMakingRecipe;
import com.chemiofitor.tponder.index.CPRecipeTypes;
import com.simibubi.create.api.behaviour.spouting.BlockSpoutingBehaviour;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class PulpSpoutingBehaviour implements BlockSpoutingBehaviour {
    @Override
    public int fillBlock(Level level, BlockPos pos, SpoutBlockEntity spout, FluidStack availableFluid, boolean simulate) {
        if (level.getBlockEntity(pos) instanceof PapermakingDepotBlockEntity depot) {
            PaperMakingRecipe recipe = findRecipe(availableFluid, new RecipeWrapper(new ItemStackHandler(0)), level);
            if (depot.getHeldItem().isEmpty() && simulate && recipe != null) {
                depot.setHeldItem(recipe.getResultItem(level.registryAccess()));
                depot.notifyUpdate();
                return recipe.getRequiredFluid().getRequiredAmount();
            }
            return 0;
        }
        return 0;
    }

    public PaperMakingRecipe findRecipe(FluidStack availableFluid, RecipeWrapper wrapper, Level level) {
        List<PaperMakingRecipe> recipe = CPRecipeTypes.PAPER_MAKING.find(wrapper, level);
        return recipe.stream().filter(r -> r.getRequiredFluid().test(availableFluid)).findFirst().orElse(null);
    }
}
