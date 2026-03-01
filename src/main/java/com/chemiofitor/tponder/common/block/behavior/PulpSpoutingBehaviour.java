package com.chemiofitor.tponder.common.block.behavior;

import com.chemiofitor.tponder.common.block.entity.PapermakingDepotBlockEntity;
import com.chemiofitor.tponder.index.CPBlocks;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPItems;
import com.simibubi.create.api.behaviour.spouting.BlockSpoutingBehaviour;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;

public class PulpSpoutingBehaviour implements BlockSpoutingBehaviour {
    @Override
    public int fillBlock(Level level, BlockPos pos, SpoutBlockEntity spout, FluidStack availableFluid, boolean simulate) {
        if (!availableFluid.getFluid().isSame(CPFluids.PULP.get()))
            return 0;
        BlockState state = level.getBlockState(pos);
        if (state.is(CPBlocks.PAPERMAKING_DEPOT.get())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity == null)
                return 0;
            IItemHandler handler = blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).orElse(null);
            if (blockEntity instanceof PapermakingDepotBlockEntity depot) {
                if (depot.getHeldItem().isEmpty() && simulate) {
                    depot.setHeldItem(new ItemStack(CPItems.WET_PAPER));
                    depot.notifyUpdate();
                    return 250;
                }
                return 0;
            }
        }
        return 0;
    }
}
