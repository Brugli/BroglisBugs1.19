package net.brogli.broglisbugs.item;

import net.brogli.broglisbugs.BroglisBugs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisBugsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BroglisBugs.MOD_ID);

    public static final RegistryObject<Item> ITEM_SLUG = ITEMS.register("item_slug",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .alwaysEat().build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
