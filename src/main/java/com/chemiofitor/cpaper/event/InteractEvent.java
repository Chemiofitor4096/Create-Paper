package com.chemiofitor.cpaper.event;

import com.chemiofitor.cpaper.index.CPBlocks;
import com.chemiofitor.cpaper.index.CPItems;
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

        if (stack.is(CPBlocks.PAPERMAKING_FRAME.asItem())) {
            if (state.is(CPBlocks.PULP_CAULDRON.get()) || state.is(CPBlocks.FINE_PULP_CAULDRON.get()) || state.is(CPBlocks.XUAN_PAPER_PULP_CAULDRON.get())) {
                if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);
                    player.getCooldowns().addCooldown(stack.getItem(), 30);
                    ItemStack result = getResultItemFromCauldron(state);
                    if (!result.isEmpty()) {
                        if (!player.addItem(result)) {
                            player.drop(result, false);
                        }
                    }
                }
                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);
            }
        }

        handleBucketPouring(event, stack, level, pos, player, hand, state);
        handleBucketFilling(event, stack, level, pos, player, hand, state);
    }

    private static void handleBucketPouring(PlayerInteractEvent.RightClickBlock event, ItemStack stack, Level level, BlockPos pos, Player player, InteractionHand hand, BlockState state) {
        // 这里保持原有逻辑，因为我们需要知道具体是哪种流体桶和对应的铁砧
        if (state.is(Blocks.CAULDRON)) {
            if (com.chemiofitor.cpaper.index.CPFluids.PULP.getBucket().isPresent() && stack.is(com.chemiofitor.cpaper.index.CPFluids.PULP.getBucket().get())) {
                var result = CauldronInteraction.emptyBucket(
                        level, pos, player, hand, stack,
                        CPBlocks.PULP_CAULDRON.getDefaultState().setValue(LayeredCauldronBlock.LEVEL, 3),
                        SoundEvents.BUCKET_EMPTY
                );
                event.setCancellationResult(result);
                event.setCanceled(true);
            } else if (com.chemiofitor.cpaper.index.CPFluids.FINE_PULP.getBucket().isPresent() && stack.is(com.chemiofitor.cpaper.index.CPFluids.FINE_PULP.getBucket().get())) {
                var result = CauldronInteraction.emptyBucket(
                        level, pos, player, hand, stack,
                        CPBlocks.FINE_PULP_CAULDRON.getDefaultState().setValue(LayeredCauldronBlock.LEVEL, 3),
                        SoundEvents.BUCKET_EMPTY
                );
                event.setCancellationResult(result);
                event.setCanceled(true);
            } else if (com.chemiofitor.cpaper.index.CPFluids.XUAN_PAPER_PULP.getBucket().isPresent() && stack.is(com.chemiofitor.cpaper.index.CPFluids.XUAN_PAPER_PULP.getBucket().get())) {
                var result = CauldronInteraction.emptyBucket(
                        level, pos, player, hand, stack,
                        CPBlocks.XUAN_PAPER_PULP_CAULDRON.getDefaultState().setValue(LayeredCauldronBlock.LEVEL, 3),
                        SoundEvents.BUCKET_EMPTY
                );
                event.setCancellationResult(result);
                event.setCanceled(true);
            }
        }
    }

    private static void handleBucketFilling(PlayerInteractEvent.RightClickBlock event, ItemStack stack, Level level, BlockPos pos, Player player, InteractionHand hand, BlockState state) {
        // 这里保持原有逻辑，因为我们需要知道具体是哪种铁砧和对应的流体桶
        if (stack.is(Items.BUCKET)) {
            if (state.is(CPBlocks.PULP_CAULDRON.get())) {
                if (com.chemiofitor.cpaper.index.CPFluids.PULP.getBucket().isPresent()) {
                    var result = CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(com.chemiofitor.cpaper.index.CPFluids.PULP.getBucket().get()), (s) -> s.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
                    event.setCancellationResult(result);
                    event.setCanceled(true);
                }
            } else if (state.is(CPBlocks.FINE_PULP_CAULDRON.get())) {
                if (com.chemiofitor.cpaper.index.CPFluids.FINE_PULP.getBucket().isPresent()) {
                    var result = CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(com.chemiofitor.cpaper.index.CPFluids.FINE_PULP.getBucket().get()), (s) -> s.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
                    event.setCancellationResult(result);
                    event.setCanceled(true);
                }
            } else if (state.is(CPBlocks.XUAN_PAPER_PULP_CAULDRON.get())) {
                if (com.chemiofitor.cpaper.index.CPFluids.XUAN_PAPER_PULP.getBucket().isPresent()) {
                    var result = CauldronInteraction.fillBucket(state, level, pos, player, hand, stack, new ItemStack(com.chemiofitor.cpaper.index.CPFluids.XUAN_PAPER_PULP.getBucket().get()), (s) -> s.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
                    event.setCancellationResult(result);
                    event.setCanceled(true);
                }
            }
        }
    }

    private static ItemStack getResultItemFromCauldron(BlockState state) {
        if (state.is(CPBlocks.PULP_CAULDRON.get())) {
            return new ItemStack(CPItems.WET_PAPER);
        } else if (state.is(CPBlocks.FINE_PULP_CAULDRON.get())) {
            return new ItemStack(Items.PAPER);
        } else if (state.is(CPBlocks.XUAN_PAPER_PULP_CAULDRON.get())) {
            return new ItemStack(CPItems.XUAN_PAPER);
        }
        return ItemStack.EMPTY;
    }
}
