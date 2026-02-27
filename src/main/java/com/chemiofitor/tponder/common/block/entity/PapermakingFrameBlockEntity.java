package com.chemiofitor.tponder.common.block.entity;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.logistics.depot.DepotBehaviour;
import com.simibubi.create.content.logistics.depot.DepotBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PapermakingFrameBlockEntity extends DepotBlockEntity {
    public PapermakingFrameBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        getBehaviour(DepotBehaviour.TYPE).onlyAccepts(itemStack -> itemStack.is(AllItems.PULP.get()));
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
    }
}
