package net.brogli.broglisbugs.event;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(BroglisBugsEntityTypes.ENTITY_SLUG.get(), EntitySlug.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_BANANA_SLUG.get(), EntityBananaSlug.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_SNAIL.get(), EntitySnail.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_LADYBIRD.get(), EntityLadybird.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(), EntityStickInsect.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_ANT.get(), EntityAnt.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_HERCULES_BEETLE.get(), EntityHerculesBeetle.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_WORM.get(), EntityWorm.setAttributes());
        event.put(BroglisBugsEntityTypes.BUG_COLLECTOR.get(), BugCollector.setAttributes());
    }
}
