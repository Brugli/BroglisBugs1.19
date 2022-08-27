package net.brogli.broglisbugs;

import com.mojang.logging.LogUtils;
import net.brogli.broglisbugs.block.BroglisBugsBlocks;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.brogli.broglisbugs.world.biomemods.BroglisBugsBiomeModifiers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BroglisBugs.MOD_ID)
public class BroglisBugs {
    public static final String MOD_ID = "broglisbugs";

    private static final Logger LOGGER = LogUtils.getLogger();

    //BroglisBugs
    public BroglisBugs()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        BroglisBugsItems.register(eventBus);
        BroglisBugsBlocks.register(eventBus);
        BroglisBugsEntityTypes.register(eventBus);

        BroglisBugsBiomeModifiers.register(eventBus);

        eventBus.addListener(this::setup);


        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_SLUG.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_BANANA_SLUG.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_SNAIL.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_LADYBIRD.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);

            SpawnPlacements.register(BroglisBugsEntityTypes.ENTITY_HERCULES_BEETLE.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Animal::checkAnimalSpawnRules);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                ItemBlockRenderTypes.setRenderLayer(BroglisBugsBlocks.BLOCK_SALT.get(), RenderType.cutout()));
    }
}
