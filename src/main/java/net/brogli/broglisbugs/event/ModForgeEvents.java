package net.brogli.broglisbugs.event;

import java.util.Random;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.brogli.broglisbugs.entity.custom.EntityWorm;
import net.brogli.broglisbugs.util.ModTagsUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

    @SubscribeEvent
    public static void HarvestDropsEvent(final BlockEvent.BreakEvent event) {
        Random random = new Random();
        float RandF = random.nextFloat();
        if (event.getLevel() instanceof Level) {
            if (ModTagsUtils.isMemberOfBlockTag(BlockTags.LEAVES, event.getState().getBlock())) {
                if (RandF >= 0.0F && RandF <= 0.05F) {
                    EntityStickInsect entityStickInsect = new EntityStickInsect(
                            BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(), (Level) event.getLevel());
                    entityStickInsect.setPos(event.getPos().getX() + 0.5F, event.getPos().getY(), event.getPos().getZ() + 0.5F);
                    event.getLevel().addFreshEntity(entityStickInsect);
                }
            }

            if (ModTagsUtils.isMemberOfBlockTag(BlockTags.DIRT, event.getState().getBlock())) {
                if (RandF >= 0.0F && RandF <= 0.02F) {
                    EntityWorm entityWorm = new EntityWorm(
                        BroglisBugsEntityTypes.ENTITY_WORM.get(), (Level) event.getLevel());
                    entityWorm.setPos(event.getPos().getX() + 0.5F, event.getPos().getY(),
                            event.getPos().getZ() + 0.5F);
                    event.getLevel().addFreshEntity(entityWorm);
                }
            }
        }
    }
}
