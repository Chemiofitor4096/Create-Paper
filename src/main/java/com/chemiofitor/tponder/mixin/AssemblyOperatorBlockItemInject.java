package com.chemiofitor.tponder.mixin;

import com.chemiofitor.tponder.index.CPBlocks;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AssemblyOperatorBlockItem.class)
public class AssemblyOperatorBlockItemInject {
    @Inject(
            remap = false,
            method = "operatesOn",
            at = @At("RETURN"),
            cancellable = true
    )
    public void add(LevelReader world, BlockPos pos, BlockState placedOnState, CallbackInfoReturnable<Boolean> cir) {
        boolean flag = cir.getReturnValue();

        flag = flag || CPBlocks.PAPERMAKING_FRAME.has(placedOnState);

        cir.setReturnValue(flag);
    }
}
