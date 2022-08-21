package net.brogli.broglisbugs.event;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.block.custom.BlockSalt;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModForgeEvents {


    @SubscribeEvent
    public static void HarvestDropsEvent(final BlockEvent.BreakEvent event ) {
        Random random = new Random();
        float RandF = random.nextFloat();
        if (RandF >= 0.0F && RandF <= 0.05F) {
        if (event.getState().getBlock() instanceof LeavesBlock && event.getLevel() instanceof Level) {
            //if (event.getState().onDestroyedByPlayer((Level) event.getLevel(), event.getPos(), event.getPlayer(), false, event.getState().getFluidState())) {
                System.out.println("Stick Insect Appeared");
                EntityStickInsect entityStickInsect = new EntityStickInsect(BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(), (Level) event.getLevel());
                entityStickInsect.setPos(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
                event.getLevel().addFreshEntity(entityStickInsect);
            }
        }
    }







}
