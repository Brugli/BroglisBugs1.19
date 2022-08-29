package net.brogli.broglisbugs.world.spawner;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.Config;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = BroglisBugs.MOD_ID)
public class SpawnHandler {
    private static Map<ResourceLocation, BugCollectorSpawner> spawners = new HashMap<>();

    @SubscribeEvent
    public static void onWorldLoad(ServerStartingEvent event)
    {
        MinecraftServer server = event.getServer();
        spawners.put(BuiltinDimensionTypes.OVERWORLD.location(), new BugCollectorSpawner(server, "BugCollector", BroglisBugsEntityTypes.BUG_COLLECTOR.get(), Config.COMMON.bugCollector));
    }

    @SubscribeEvent
    public static void onServerStart(ServerStoppedEvent event)
    {
        spawners.clear();
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event)
    {
        if(event.phase != TickEvent.Phase.START)
            return;

        if(event.side != LogicalSide.SERVER)
            return;

        BugCollectorSpawner spawner = spawners.get(event.level.dimension().location());
        if(spawner != null)
        {
            spawner.tick(event.level);
        }
    }
}
