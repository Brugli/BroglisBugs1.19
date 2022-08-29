package net.brogli.broglisbugs.world.spawner;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public class BugCollectorData {
    private WanderingBugCollectorData data;
    private int wanderingBugCollectorSpawnDelay;
    private int wanderingBugCollectorSpawnChance;

    public BugCollectorData(WanderingBugCollectorData data)
    {
        this.data = data;
    }

    public void setWanderingBugCollectorSpawnDelay(int wanderingBugCollectorSpawnDelay)
    {
        this.wanderingBugCollectorSpawnDelay = wanderingBugCollectorSpawnDelay;
        this.data.setDirty(true);
    }

    public void setWanderingBugCollectorSpawnChance(int wanderingBugCollectorSpawnChance)
    {
        this.wanderingBugCollectorSpawnChance = wanderingBugCollectorSpawnChance;
        this.data.setDirty(true);
    }

    public int getWanderingBugCollectorSpawnDelay()
    {
        return wanderingBugCollectorSpawnDelay;
    }

    public int getWanderingBugCollectorSpawnChance()
    {
        return wanderingBugCollectorSpawnChance;
    }

    public void read(CompoundTag compound)
    {
        if(compound.contains("WanderingBugCollectorSpawnDelay", Tag.TAG_INT))
        {
            this.wanderingBugCollectorSpawnDelay = compound.getInt("WanderingBugCollectorSpawnDelay");
        }
        if(compound.contains("WanderingBugCollectorSpawnChance", Tag.TAG_INT))
        {
            this.wanderingBugCollectorSpawnChance = compound.getInt("WanderingBugCollectorSpawnChance");
        }
    }

    public CompoundTag write(CompoundTag compound)
    {
        compound.putInt("WanderingBugCollectorSpawnDelay", this.wanderingBugCollectorSpawnDelay);
        compound.putInt("WanderingBugCollectorSpawnChance", this.wanderingBugCollectorSpawnChance);
        return compound;
    }
}
