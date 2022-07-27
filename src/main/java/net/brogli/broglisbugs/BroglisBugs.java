package net.brogli.broglisbugs;

import com.mojang.logging.LogUtils;
import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.client.EntitySlugRenderer;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
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
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();


        BroglisBugsItems.register(eventBus);
        BroglisBugsEntityTypes.register(eventBus);

        eventBus.addListener(this::setup);

        GeckoLib.initialize();
        GeckoLibMod.DISABLE_IN_DEV = true;

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
}
