package net.brogli.broglisbugs;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static class Common {
        public final BugCollector bugCollector;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Common configuration settings").push("common");
            this.bugCollector = new BugCollector(builder, "Bug Collector", "bug_collector", 5, 48000, -64, 320);
            builder.pop();
        }

        public static class BugCollector {
            public final ForgeConfigSpec.IntValue traderSpawnChance;
            public final ForgeConfigSpec.IntValue traderSpawnDelay;
            public final ForgeConfigSpec.IntValue traderMinSpawnLevel;
            public final ForgeConfigSpec.IntValue traderMaxSpawnLevel;
            public final ForgeConfigSpec.IntValue restockDelay;

            BugCollector(ForgeConfigSpec.Builder builder, String name, String key, int spawnChance, int spawnDelay, int minLevel, int maxLevel) {
                builder.comment(name + " settings").push(key);
                this.traderSpawnChance = builder
                        .comment("The chance out of one hundred that the trader will spawn in the over world")
                        .defineInRange("traderSpawnChance", spawnChance, 1, 100);
                this.traderSpawnDelay = builder
                        .comment("The amount of ticks before the trader will spawn again")
                        .defineInRange("traderSpawnDelay", spawnDelay, 0, Integer.MAX_VALUE);
                this.traderMinSpawnLevel = builder
                        .comment("The minimum level the trader can spawn")
                        .defineInRange("traderMinSpawnLevel", minLevel, -64, 320);
                this.traderMaxSpawnLevel = builder
                        .comment("The maximum level the trader can spawn")
                        .defineInRange("traderMaxSpawnLevel", maxLevel, -64, 320);
                this.restockDelay = builder
                        .comment("The amount of ticks before the trader will replenish it's trades. Set to -1 to disable restocking")
                        .defineInRange("restockDelay", 48000, -1, Integer.MAX_VALUE);
                builder.pop();
            }
        }
    }
    static final ForgeConfigSpec commonSpec;
    public static final Config.Common COMMON;

    static
    {
        final Pair<Common, ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(Config.Common::new);
        commonSpec = commonPair.getRight();
        COMMON = commonPair.getLeft();
    }
}
