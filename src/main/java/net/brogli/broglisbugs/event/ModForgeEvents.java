package net.brogli.broglisbugs.event;

import java.util.Random;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.brogli.broglisbugs.entity.custom.EntityWorm;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {

    @SubscribeEvent
    public static void HarvestDropsEvent(final BlockEvent.BreakEvent event ) {
        Random random = new Random();
        float RandF = random.nextFloat();
        if (event.getLevel() instanceof Level){
            if (event.getState().getBlock() instanceof LeavesBlock){
                if (RandF >= 0.0F && RandF <= 0.05F) {
                    System.out.println("Stick Insect Appeared");
                    EntityStickInsect entityStickInsect = new EntityStickInsect(BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(), (Level) event.getLevel());
                    entityStickInsect.setPos(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
                    event.getLevel().addFreshEntity(entityStickInsect);
                }
            }
            if (event.getState().getBlock() instanceof GrassBlock){
                if (RandF >= 0.0F && RandF <= 0.02F) {
                    System.out.println("Worm Appeared");
                    EntityWorm entityWorm = new EntityWorm(BroglisBugsEntityTypes.ENTITY_WORM.get(), (Level) event.getLevel());
                    entityWorm.setPos(event.getPos().getX() + 0.5F, event.getPos().getY(), event.getPos().getZ() + 0.5F);
                    event.getLevel().addFreshEntity(entityWorm);
                }
            }
        }
    }
}

