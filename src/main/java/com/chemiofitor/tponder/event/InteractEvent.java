package com.chemiofitor.tponder.event;

import com.chemiofitor.tponder.index.CPBlocks;
import com.chemiofitor.tponder.index.CPFluids;
import com.chemiofitor.tponder.index.CPItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.chemiofitor.tponder.index.CPFluids.FINE_PULP;
import static com.chemiofitor.tponder.index.CPFluids.PULP;

@Mod.EventBusSubscriber
public class InteractEvent {

    @SubscribeEvent
    public static void act(PlayerInteractEvent.RightClickBlock event) {
        ItemStack stack = event.getItemStack();
        Level level =  event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        BlockState state = level.getBlockState(pos);

        if (state.is(Blocks.CAULDRON) && stack.is(PULP.getBucket().orElse(null))) {
            var result = CauldronInteraction.emptyBucket(
                    level, pos, player, hand, stack,
                    CPBlocks.PULP_CAULDRON.getDefaultState().setValue(LayeredCauldronBlock.LEVEL, 3),
                    SoundEvents.BUCKET_EMPTY
            );
            event.setCancellationResult(result);
            event.setCanceled(true);
        } else if (state.is(CPBlocks.PULP_CAULDRON.get()) && stack.is(Items.BUCKET)) {
            var result = CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(CPFluids.PULP.getBucket().orElseThrow()), (s) -> s.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
            event.setCancellationResult(result);
            event.setCanceled(true);
        } else if (state.is(CPBlocks.PULP_CAULDRON.get())  && stack.is(CPBlocks.PAPERMAKING_FRAME.asItem())) {
            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                player.getCooldowns().addCooldown(stack.getItem(), 30);
                ItemStack paper = new ItemStack(CPItems.WET_PAPER);
                if (!player.addItem(paper)) {
                    player.drop(paper, false);
                }
            }
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }

        if (state.is(Blocks.CAULDRON) && stack.is(FINE_PULP.getBucket().orElse(null))) {
            var result = CauldronInteraction.emptyBucket(
                    level, pos, player, hand, stack,
                    CPBlocks.FINE_PULP_CAULDRON.getDefaultState().setValue(LayeredCauldronBlock.LEVEL, 3),
                    SoundEvents.BUCKET_EMPTY
            );
            event.setCancellationResult(result);
            event.setCanceled(true);
        } else if (state.is(CPBlocks.FINE_PULP_CAULDRON.get()) && stack.is(Items.BUCKET)) {
            var result = CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(FINE_PULP.getBucket().orElseThrow()), (s) -> s.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
            event.setCancellationResult(result);
            event.setCanceled(true);
        } else if (state.is(CPBlocks.FINE_PULP_CAULDRON.get())  && stack.is(CPBlocks.PAPERMAKING_FRAME.asItem())) {
            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                player.getCooldowns().addCooldown(stack.getItem(), 30);
                ItemStack paper = new ItemStack(Items.PAPER);
                if (!player.addItem(paper)) {
                    player.drop(paper, false);
                }
            }
            event.setCancellationResult(InteractionResult.SUCCESS);
            event.setCanceled(true);
        }
    }
}
