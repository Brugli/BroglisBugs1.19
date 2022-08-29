package net.brogli.broglisbugs.world.spawner;

import net.brogli.broglisbugs.BroglisBugs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;

public class WanderingBugCollectorData extends SavedData {

    private static final String DATA_NAME = BroglisBugs.MOD_ID + "_bug_collector";

    private final Map<String, BugCollectorData> data = new HashMap<>();

    public WanderingBugCollectorData() {}

    public BugCollectorData getBugCollectorData(String key)
    {
        return this.data.computeIfAbsent(key, s -> new BugCollectorData(this));
    }

    public WanderingBugCollectorData read(CompoundTag tag)
    {
        if(tag.contains("WanderingBugCollectorSpawnDelay", Tag.TAG_INT))
        {
            this.getBugCollectorData("WanderingBugCollector").setWanderingBugCollectorSpawnDelay(tag.getInt("WanderingBugCollectorSpawnDelay"));
        }
        if(tag.contains("WanderingBugCollectorSpawnChance", Tag.TAG_INT))
        {
            this.getBugCollectorData("WanderingBugCollector").setWanderingBugCollectorSpawnChance(tag.getInt("WanderingBugCollectorSpawnChance"));
        }
        if(tag.contains("Data", Tag.TAG_LIST))
        {
            this.data.clear();
            ListTag list = tag.getList("Data", Tag.TAG_COMPOUND);
            list.forEach(nbt -> {
                CompoundTag bugCollectorTag = (CompoundTag) nbt;
                String key = bugCollectorTag.getString("Key");
                BugCollectorData data = new BugCollectorData(this);
                data.read(bugCollectorTag);
                this.data.put(key, data);
            });
        }
        return this;
    }

    @Override
    public CompoundTag save(CompoundTag compound)
    {
        ListTag list = new ListTag();
        this.data.forEach((s, bugCollectorData) -> {
            CompoundTag bugCollectorTag = new CompoundTag();
            bugCollectorData.write(bugCollectorTag);
            bugCollectorTag.putString("Key", s);
            list.add(bugCollectorTag);
        });
        compound.put("Data", list);
        return compound;
    }

    public static WanderingBugCollectorData get(MinecraftServer server)
    {
        ServerLevel level = server.getLevel(Level.OVERWORLD);
        return level.getDataStorage().computeIfAbsent(tag -> new WanderingBugCollectorData().read(tag), WanderingBugCollectorData::new, DATA_NAME);
    }
}
