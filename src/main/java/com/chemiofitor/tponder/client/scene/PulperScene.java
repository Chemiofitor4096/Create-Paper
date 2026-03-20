package com.chemiofitor.tponder.client.scene;

import com.chemiofitor.tponder.common.block.entity.MechanicalPulperBlockEntity;
import com.chemiofitor.tponder.index.CPFluids;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class PulperScene {
    public static void pulping(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("mechanical_pulper", "Pulping Items with the Mechanical Pulper");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.idle(5);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(2, 2, 2), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(2, 4, 2), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().fromTo(3, 1, 5, 1, 4, 3), Direction.SOUTH);
        scene.idle(20);

        BlockPos basin = util.grid().at(2, 2, 2);
        BlockPos pulper = util.grid().at(2, 4, 2);
        Vec3 basinSide = util.vector().blockSurface(basin, Direction.WEST);

        scene.world().setKineticSpeed(util.select().position(pulper), 64);

        scene.idle(20);
        scene.overlay().showText(60)
                .pointAt(basinSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Pulper is used to pulping automatically");
        scene.idle(40);

        ItemStack sapling = new ItemStack(Items.OAK_SAPLING);
        ItemStack water = new ItemStack(Items.WATER_BUCKET);

        scene.overlay().showControls(util.vector().topOf(basin), Pointing.LEFT, 30).withItem(sapling);
        scene.overlay().showControls(util.vector().topOf(basin), Pointing.RIGHT, 30).withItem(water);
        scene.idle(30);
        Class<MechanicalPulperBlockEntity> type = MechanicalPulperBlockEntity.class;
        scene.world().modifyBlockEntity(pulper, type, MechanicalPulperBlockEntity::startProcessingBasin);
        scene.world().createItemOnBeltLike(basin, Direction.UP, sapling);
        scene.world().modifyBlockEntity(basin, BasinBlockEntity.class, b -> b.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(c -> {
            c.fill(new FluidStack(Fluids.WATER, 1000), IFluidHandler.FluidAction.EXECUTE);
        }));
        scene.idle(80);
        scene.world().modifyBlockEntity(basin, BasinBlockEntity.class, b -> {
            b.inputInventory.clearContent();
            b.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(c -> {
                c.drain(1000, IFluidHandler.FluidAction.EXECUTE);
                c.fill(new FluidStack(CPFluids.PULP.getSource(), 1000), IFluidHandler.FluidAction.EXECUTE);
            });
        });

        scene.rotateCameraY(-30);
        scene.idle(10);
        scene.world().setBlock(basin.below(), AllBlocks.BLAZE_BURNER.getDefaultState()
                .setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.KINDLED), true);
        scene.idle(10);

        scene.overlay().showText(80)
                .pointAt(basinSide.subtract(0, 1, 0))
                .placeNearTarget()
                .text("Some of those recipes may require the heat of a Blaze Burner");
        scene.idle(40);

        scene.rotateCameraY(30);

        scene.idle(60);
        Vec3 filterPos = util.vector().of(2, 2.75f, 2.5f);
        scene.overlay().showFilterSlotInput(filterPos, Direction.WEST, 100);
        scene.overlay().showText(100)
                .pointAt(filterPos)
                .placeNearTarget()
                .attachKeyFrame()
                .text("The filter slot can be used in case two recipes are conflicting.");
        scene.idle(80);
        scene.markAsFinished();
    }
}
