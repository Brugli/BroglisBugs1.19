package net.brogli.broglisbugs.world.spawner;

import net.brogli.broglisbugs.Config;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.custom.BugCollector;
import net.brogli.broglisbugs.entity.custom.EntityLadybird;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.horse.TraderLlama;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nullable;
import java.util.List;

public class BugCollectorSpawner {
    private final BugCollectorData data;
    private final EntityType<? extends BugCollector> entityType;

    private final RandomSource random = RandomSource.create();
    private int delayBeforeSpawnLogic;
    private final int traderSpawnDelay;
    private final int traderSpawnChance;
    private int currentTraderSpawnDelay;
    private int currentTraderSpawnChance;
    private int minLevel;
    private int maxLevel;

    public BugCollectorSpawner(MinecraftServer server, String key, EntityType<? extends BugCollector> entityType, Config.Common.BugCollector bugCollector)
    {
        this.data = WanderingBugCollectorData.get(server).getBugCollectorData(key);
        this.entityType = entityType;
        this.delayBeforeSpawnLogic = 600;
        this.currentTraderSpawnDelay = this.data.getWanderingBugCollectorSpawnDelay();
        this.currentTraderSpawnChance = this.data.getWanderingBugCollectorSpawnChance();
        this.traderSpawnDelay = bugCollector.traderSpawnDelay.get();
        this.traderSpawnChance = bugCollector.traderSpawnChance.get();
        this.minLevel = Math.min(bugCollector.traderMinSpawnLevel.get(), bugCollector.traderMaxSpawnLevel.get());
        this.maxLevel = Math.max(bugCollector.traderMinSpawnLevel.get(), bugCollector.traderMaxSpawnLevel.get());
        if(this.currentTraderSpawnDelay == 0 && this.currentTraderSpawnChance == 0)
        {
            this.currentTraderSpawnDelay = this.traderSpawnDelay;
            this.currentTraderSpawnChance = this.traderSpawnChance;
            this.data.setWanderingBugCollectorSpawnDelay(this.currentTraderSpawnDelay);
            this.data.setWanderingBugCollectorSpawnChance(this.currentTraderSpawnChance);
        }
    }

    public int tick(Level level)
    {
        if(level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING))
        {
            if(--this.delayBeforeSpawnLogic <= 0)
            {
                int delay = Math.max(this.traderSpawnDelay / 20, 1);
                this.delayBeforeSpawnLogic = delay;
                this.currentTraderSpawnDelay -= delay;
                this.data.setWanderingBugCollectorSpawnDelay(this.currentTraderSpawnDelay);
                if(this.currentTraderSpawnDelay <= 0)
                {
                    this.currentTraderSpawnDelay = this.traderSpawnDelay;
                    if(level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
                    {
                        int spawnChance = this.currentTraderSpawnChance;
                        this.currentTraderSpawnChance = Mth.clamp(this.currentTraderSpawnChance + this.traderSpawnChance, this.traderSpawnChance, 100);
                        this.data.setWanderingBugCollectorSpawnChance(this.currentTraderSpawnChance);
                        if(level.getRandom().nextInt(100) <= spawnChance)
                        {
                            if(this.spawnTrader(level))
                            {
                                this.currentTraderSpawnChance = this.traderSpawnChance;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private boolean spawnTrader(Level level)
    {
        List<? extends Player> players = level.players();
        if(players.isEmpty())
        {
            return false;
        }
        Player randomPlayer = players.get(level.getRandom().nextInt(players.size()));
        if(randomPlayer == null)
        {
            return true;
        }
        else
        {
            BlockPos blockpos = randomPlayer.getOnPos();
            BlockPos safestPos = this.getSafePositionAroundPlayer(randomPlayer.level, blockpos, 10);
            if(safestPos != null && this.isEmptyCollision(randomPlayer.level, safestPos))
            {
                if(level.getBiome(safestPos).is(Biomes.THE_VOID))
                {
                    return false;
                }
                if(safestPos.getY() < this.minLevel || safestPos.getY() >= this.maxLevel)
                {
                    return false;
                }
                BugCollector bugCollector = this.entityType.spawn((ServerLevel) randomPlayer.level, null, null, null, safestPos, MobSpawnType.EVENT, false, false);
                if(bugCollector != null)
                    /**for(int j = 0; j < 2; ++j) {
                        this.tryToSpawnBugsFor((ServerLevel) randomPlayer.level, bugCollector, 4);
                    }**/
                {
                    bugCollector.setDespawnDelay(this.traderSpawnDelay);
                    bugCollector.restrictTo(safestPos, 16);
                    return true;
                }
            }
            return false;
        }
    }

    @Nullable
    private BlockPos getSafePositionAroundPlayer(Level level, BlockPos pos, int range)
    {
        if(range == 0)
        {
            return null;
        }
        BlockPos safestPos = null;
        for(int attempts = 0; attempts < 50; attempts++)
        {
            int posX = pos.getX() + level.getRandom().nextInt(range * 2) - range;
            int posY = pos.getY() + level.getRandom().nextInt(range) - range / 2;
            int posZ = pos.getZ() + level.getRandom().nextInt(range * 2) - range;
            BlockPos testPos = this.findGround(level, new BlockPos(posX, posY, posZ), range);
            if(testPos != null && NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, level, testPos, this.entityType))
            {
                safestPos = testPos;
                break;
            }
        }
        return safestPos != null ? safestPos : this.getSafePositionAroundPlayer(level, pos, range / 2);
    }

    @Nullable
    private BlockPos findGround(Level level, BlockPos pos, int maxDistance)
    {
        if(level.getBlockState(pos).isAir())
        {
            BlockPos downPos = pos;
            while(Level.isInSpawnableBounds(downPos.below()) && level.getBlockState(downPos.below()).isAir() && downPos.below().closerThan(pos, maxDistance))
            {
                downPos = downPos.below();
            }
            if(!level.getBlockState(downPos.below()).isAir())
            {
                return downPos;
            }
        }
        else
        {
            BlockPos upPos = pos;
            while(Level.isInSpawnableBounds(upPos.above()) && !level.getBlockState(upPos.above()).isAir() && upPos.above().closerThan(pos, maxDistance))
            {
                upPos = upPos.above();
            }
            if(!level.getBlockState(upPos.below()).isAir())
            {
                return upPos;
            }
        }
        return null;
    }

    private boolean isEmptyCollision(Level level, BlockPos pos)
    {
        return level.getBlockState(pos).getCollisionShape(level, pos).isEmpty();
    }

    /**private void tryToSpawnBugsFor(ServerLevel p_35918_, BugCollector p_35919_, int p_35920_) {
        BlockPos blockpos = this.findSpawnPositionNear(p_35918_, p_35919_.blockPosition(), p_35920_);
        if (blockpos != null) {
            EntityLadybird entityLadybird = BroglisBugsEntityTypes.ENTITY_LADYBIRD.get().spawn(p_35918_, (CompoundTag)null, (Component)null, (Player)null, blockpos, MobSpawnType.EVENT, false, false);
            if (entityLadybird != null) {
                entityLadybird.setLeashedTo(p_35919_, true);
            }
        }
    }

    @Nullable
    private BlockPos findSpawnPositionNear(LevelReader p_35929_, BlockPos p_35930_, int p_35931_) {
        BlockPos blockpos = null;

        for(int i = 0; i < 10; ++i) {
            int j = p_35930_.getX() + this.random.nextInt(p_35931_ * 2) - p_35931_;
            int k = p_35930_.getZ() + this.random.nextInt(p_35931_ * 2) - p_35931_;
            int l = p_35929_.getHeight(Heightmap.Types.WORLD_SURFACE, j, k);
            BlockPos blockpos1 = new BlockPos(j, l, k);
            if (NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, p_35929_, blockpos1, BroglisBugsEntityTypes.BUG_COLLECTOR.get())) {
                blockpos = blockpos1;
                break;
            }
        }

        return blockpos;
    }**/
}
