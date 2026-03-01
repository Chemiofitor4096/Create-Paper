package com.chemiofitor.tponder.client.visual;

import com.chemiofitor.tponder.common.block.entity.MechanicalPulperBlockEntity;
import com.chemiofitor.tponder.index.CPPartialModels;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.RotatingInstance;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.OrientedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.minecraft.core.Direction;

import java.util.function.Consumer;

public class PulpVisual extends SingleAxisRotatingVisual<MechanicalPulperBlockEntity> implements SimpleDynamicVisual {
    private final RotatingInstance pulperHead;
    private final OrientedInstance pulperPole;
    private final MechanicalPulperBlockEntity pulper;

    public PulpVisual(VisualizationContext context, MechanicalPulperBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick, Models.partial(AllPartialModels.SHAFTLESS_COGWHEEL));
        this.pulper = blockEntity;

        pulperHead = instancerProvider().instancer(AllInstanceTypes.ROTATING, Models.partial(CPPartialModels.MECHANICAL_PULPER_HEAD))
                .createInstance();

        pulperHead.setRotationAxis(Direction.Axis.Y);

        pulperPole = instancerProvider().instancer(InstanceTypes.ORIENTED, Models.partial(CPPartialModels.MECHANICAL_PULPER_POLE))
                .createInstance();

        animate(partialTick);
    }

    @Override
    public void beginFrame(DynamicVisual.Context ctx) {
        animate(ctx.partialTick());
    }


    private void animate(float pt) {
        float renderedHeadOffset = pulper.getRenderedHeadOffset(pt);

        transformPole(renderedHeadOffset);
        transformHead(renderedHeadOffset, pt);
    }


    private void transformHead(float renderedHeadOffset, float pt) {
        float speed = pulper.getRenderedHeadRotationSpeed(pt);

        pulperHead.setPosition(getVisualPosition())
                .nudge(0, -renderedHeadOffset, 0)
                .setRotationalSpeed(speed * 2 * RotatingInstance.SPEED_MULTIPLIER)
                .setChanged();
    }

    private void transformPole(float renderedHeadOffset) {
        pulperPole.position(getVisualPosition())
                .translatePosition(0, -renderedHeadOffset, 0)
                .setChanged();
    }

    @Override
    public void updateLight(float partialTick) {
        super.updateLight(partialTick);

        relight(pos.below(), pulperHead);
        relight(pulperPole);
    }

    @Override
    protected void _delete() {
        super._delete();
        pulperHead.delete();
        pulperPole.delete();
    }

    @Override
    public void collectCrumblingInstances(Consumer<Instance> consumer) {
        super.collectCrumblingInstances(consumer);
        consumer.accept(pulperHead);
        consumer.accept(pulperPole);
    }
}
