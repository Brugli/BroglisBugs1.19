package net.brogli.broglisbugs.world.biomemods;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.brogli.broglisbugs.BroglisBugs;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisBugsBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, BroglisBugs.MOD_ID);

    public static RegistryObject<Codec<BroglisBugsEntityBiomeModifier>> ENTITY_MODIFIER = BIOME_MODIFIERS.register("entities", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(BroglisBugsEntityBiomeModifier::biomes),
                    MobSpawnSettings.SpawnerData.CODEC.fieldOf("entity").forGetter(BroglisBugsEntityBiomeModifier::spawnerData)
            ).apply(builder, BroglisBugsEntityBiomeModifier::new)));


    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }
}
