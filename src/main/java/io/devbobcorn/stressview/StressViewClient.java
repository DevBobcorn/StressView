package io.devbobcorn.stressview;

import io.devbobcorn.stressview.client.AdvancedStressometerRenderer;
import io.devbobcorn.stressview.client.AdvancedStressometerVisual;
import io.devbobcorn.stressview.client.StressViewPartialModels;

import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = StressViewMod.MODID, dist = Dist.CLIENT)
public class StressViewClient {
    public StressViewClient(IEventBus modEventBus, ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        StressViewPartialModels.init();

        modEventBus.addListener(StressViewClient::onClientSetup);
        modEventBus.addListener(StressViewClient::registerRenderers);
    }

    static void onClientSetup(FMLClientSetupEvent event) {
        StressViewMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        StressViewMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        SimpleBlockEntityVisualizer.builder(StressViewMod.ADVANCED_STRESSOMETER_BE.get())
                .factory(AdvancedStressometerVisual::new)
                .skipVanillaRender(be -> true)
                .apply();
    }

    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(StressViewMod.ADVANCED_STRESSOMETER_BE.get(),
                AdvancedStressometerRenderer::new);
    }
}
