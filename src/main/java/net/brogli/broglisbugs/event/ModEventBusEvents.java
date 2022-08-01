package net.brogli.broglisbugs.event;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(BroglisBugsEntityTypes.ENTITY_SLUG.get(), EntitySlug.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_SNAIL.get(), EntitySnail.setAttributes());
        event.put(BroglisBugsEntityTypes.ENTITY_LADYBIRD.get(), EntitySnail.setAttributes());
    }
}
