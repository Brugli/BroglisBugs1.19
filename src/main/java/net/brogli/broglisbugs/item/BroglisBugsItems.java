package net.brogli.broglisbugs.item;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.item.custom.ItemAnt;
import net.brogli.broglisbugs.item.custom.ItemBananaSlug;
import net.brogli.broglisbugs.item.custom.ItemLadybird;
import net.brogli.broglisbugs.item.custom.ItemSlug;
import net.brogli.broglisbugs.item.custom.ItemSnail;
import net.brogli.broglisbugs.item.custom.ItemStickInsect;
import net.brogli.broglisbugs.item.tool.ItemBugNet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisBugsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BroglisBugs.MOD_ID);

    //Slug
    public static final RegistryObject<ItemSlug> ITEM_SLUG = ITEMS.register("item_slug",
            () -> new ItemSlug(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 4, false, true), 1)
                            .alwaysEat().build())));

    //Banana Slug
    public static final RegistryObject<ItemBananaSlug> ITEM_BANANA_SLUG = ITEMS.register("item_banana_slug",
            () -> new ItemBananaSlug(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, 400, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 4, false, true), 1)
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
    public static final RegistryObject<ItemSnail> ITEM_SNAIL = ITEMS.register("item_snail",
            () -> new ItemSnail(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300, 4, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300, 1, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300, 4, false, true), 1)
                            .alwaysEat().build())));

    // Cooked Snail
    public static final RegistryObject<Item> ITEM_COOKED_SNAIL = ITEMS.register("item_cooked_snail",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(3).saturationMod(2).alwaysEat().build())));

    // Bottle Of Slime
    public static final RegistryObject<Item> ITEM_SLIME_BOTTLE = ITEMS.register("item_slime_bottle",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)));

    public static final RegistryObject<ItemLadybird> ITEM_LADYBIRD = ITEMS.register("item_ladybird",
            () -> new ItemLadybird(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0f)
                            .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 2, false, true), 1)
                            .alwaysEat().build())));

    // Stick Insect
    public static final RegistryObject<ItemStickInsect> ITEM_STICK_INSECT = ITEMS.register("item_stick_insect",
            () -> new ItemStickInsect(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0f)
                            .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 100, 2, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2, false, true), 1)
                            .alwaysEat().build())));

    public static final RegistryObject<Item> ITEM_FRIED_STICK_INSECT = ITEMS.register("item_fried_stick_insect",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 200, 2, false, true), 1)
                            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 2, false, true), 1)
                            .alwaysEat().build())));

    // Grasshopper
    public static final RegistryObject<Item> ITEM_GRASSHOPPER = ITEMS.register("item_grasshopper",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 150, 1, false, true), 1)
                            .alwaysEat().build())));

    // Cooked Grasshopper
    public static final RegistryObject<Item> ITEM_COOKED_GRASSHOPPER = ITEMS.register("item_cooked_grasshopper",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.5f)
                            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 300, 3, false, true), 1)
                            .alwaysEat().build())));

    public static final RegistryObject<ItemAnt> ITEM_ANT = ITEMS.register("item_ant",
            () -> new ItemAnt(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)
                    .food(new FoodProperties.Builder().nutrition(1).saturationMod(0).alwaysEat().build())));

    // Tools

    // Bug Net
    public static final RegistryObject<ItemBugNet> ITEM_BUG_NET = ITEMS.register("item_bug_net",
            () -> new ItemBugNet(BroglisBugsTiers.BUG_NET, new Item.Properties().tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)));

    // Netting
    public static final RegistryObject<Item> ITEM_NETTING = ITEMS.register("item_netting",
            () -> new Item(new Item.Properties().stacksTo(64).tab(BroglisBugsCreativeModeTab.BROGLISBUGS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
