package net.brogli.broglisbugs.event;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.client.BugCollectorRenderer;
import net.brogli.broglisbugs.entity.client.EntityAntRenderer;
import net.brogli.broglisbugs.entity.client.EntityBananaSlugRenderer;
import net.brogli.broglisbugs.entity.client.EntityHerculesBeetleRenderer;
import net.brogli.broglisbugs.entity.client.EntityLadybirdRenderer;
import net.brogli.broglisbugs.entity.client.EntitySlugRenderer;
import net.brogli.broglisbugs.entity.client.EntitySnailRenderer;
import net.brogli.broglisbugs.entity.client.EntityStickInsectRenderer;
import net.brogli.broglisbugs.entity.client.EntityWormRenderer;
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
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_BANANA_SLUG.get(), EntityBananaSlugRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_SNAIL.get(), EntitySnailRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_LADYBIRD.get(), EntityLadybirdRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(), EntityStickInsectRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_ANT.get(), EntityAntRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_HERCULES_BEETLE.get(), EntityHerculesBeetleRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.ENTITY_WORM.get(), EntityWormRenderer::new);
        EntityRenderers.register(BroglisBugsEntityTypes.BUG_COLLECTOR.get(), BugCollectorRenderer::new);

    }

}
