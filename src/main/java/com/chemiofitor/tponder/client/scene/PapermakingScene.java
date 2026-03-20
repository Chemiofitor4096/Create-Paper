package com.chemiofitor.tponder.client.scene;

import com.chemiofitor.tponder.index.CPFluids;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class PapermakingScene {
    public static void filling(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("papermaking_filling", "Pulping Items with the Mechanical Pulper");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);
        scene.idle(5);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(2, 3, 2), Direction.DOWN);
        scene.idle(20);

        BlockPos depot = util.grid().at(2, 1, 2);
        BlockPos spout = util.grid().at(2, 3, 2);
        Vec3 spoutSide = util.vector().blockSurface(spout, Direction.WEST);

        scene.idle(20);
        scene.overlay().showText(60)
                .pointAt(spoutSide)
                .placeNearTarget()
                .attachKeyFrame()
                .text("Spout can be used to make paper");
        scene.idle(80);

        scene.world().modifyBlockEntity(spout, SpoutBlockEntity.class, b -> {
            b.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(c -> {
                c.fill(new FluidStack(CPFluids.PULP.getSource(), 1000), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(40);
        scene.markAsFinished();
    }
}
