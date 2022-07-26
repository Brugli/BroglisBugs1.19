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

    //Slug
    public static final RegistryObject<Item> ITEM_SLUG = ITEMS.register("item_slug",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .alwaysEat().build())));

    //Banana Slug
    public static final RegistryObject<Item> ITEM_BANANA_SLUG = ITEMS.register("item_banana_slug",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .alwaysEat().build())));

    // Crispy Slug
    public static final RegistryObject<Item> ITEM_CRISPY_SLUG = ITEMS.register("item_crispy_slug",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(1)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .alwaysEat().build())));

    // Snail
    public static final RegistryObject<Item> ITEM_SNAIL = ITEMS.register("item_snail",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300, 1, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, true), 1)
                            .alwaysEat().build())));

    // Cooked Snail
    public static final RegistryObject<Item> ITEM_COOKED_SNAIL = ITEMS.register("item_cooked_snail",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2).alwaysEat().build())));

    // Bottle Of Slime
    public static final RegistryObject<Item> ITEM_SLIME_BOTTLE = ITEMS.register("item_slime_bottle",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)));

    // Stick Insect
    public static final RegistryObject<Item> ITEM_STICK_INSECT = ITEMS.register("item_stick_insect",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f).alwaysEat().build())));

    // Grasshopper
    public static final RegistryObject<Item> ITEM_GRASSHOPPER = ITEMS.register("item_grasshopper",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 150, 2, false, true), 1)
                            .alwaysEat().build())));

    // Cooked Grasshopper
    public static final RegistryObject<Item> ITEM_COOKED_GRASSHOPPER = ITEMS.register("item_cooked_grasshopper",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 300, 4, false, true), 1)
                            .alwaysEat().build())));

    // Tools

    // Bug Net
    /**public static final RegistryObject<ItemBugNet> ITEM_BUG_NET = ITEMS.register("item_bug_net",
            () -> new ItemBugNet(BroglisBugsTiers.BUG_NET, new Item.Properties().tab(BroglisBugs.BROGLIS_BUGS_TAB)));
**/
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
