package io.devbobcorn.stressview.client;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import io.devbobcorn.stressview.StressViewMod;
import net.minecraft.resources.ResourceLocation;

public class StressViewPartialModels {

    public static final PartialModel ADV_STRESSOMETER_HEAD =
        PartialModel.of(ResourceLocation.fromNamespaceAndPath(StressViewMod.MODID, "block/advanced_stressometer/head"));

    public static void init() {
        // Force class loading to register partial models with Flywheel
    }
}
