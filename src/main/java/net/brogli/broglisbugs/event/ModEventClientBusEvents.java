package net.brogli.broglisbugs.event;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.client.EntitySlugRenderer;
import net.brogli.broglisbugs.entity.client.EntitySnailRenderer;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {

        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_SLUG.get(), EntitySlugRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_SNAIL.get(), EntitySnailRenderer::new);

    }
}
