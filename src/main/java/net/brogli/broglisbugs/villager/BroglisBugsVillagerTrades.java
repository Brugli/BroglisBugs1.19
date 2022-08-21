package net.brogli.broglisbugs.villager;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.brogli.broglisbugs.block.BroglisBugsBlocks;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class BroglisBugsVillagerTrades extends VillagerTrades {

    public static final Int2ObjectMap<ItemListing[]> BUG_COLLECTOR_TRADES = toIntMap(ImmutableMap.of(1, new BroglisBugsVillagerTrades.ItemListing[]{
            new BroglisBugsVillagerTrades.ItemsForSlugs(BroglisBugsBlocks.BLOCK_SALT.get(), 1, 5, 10, 1)},
            2, new BroglisBugsVillagerTrades.ItemListing[]{new BroglisBugsVillagerTrades.ItemsForBannanaSlugs(BroglisBugsBlocks.BLOCK_SALT.get(), 1, 10, 10, 1)},
            3, new BroglisBugsVillagerTrades.ItemListing[]{new BroglisBugsVillagerTrades.ItemsForSnails(BroglisBugsBlocks.BLOCK_SALT.get(), 1, 5, 10, 1)}));

    private static Int2ObjectMap<BroglisBugsVillagerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, BroglisBugsVillagerTrades.ItemListing[]> map) {
        return new Int2ObjectOpenHashMap<>(map);
    }

    public interface ItemListing {
        @Nullable
        MerchantOffer getOffer(Entity entity, RandomSource source);
    }

    static class ItemsForBannanaSlugs implements BroglisBugsVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int bugCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForBannanaSlugs(Block block, int a, int b, int c, int d) {
            this(new ItemStack(block), a, b, c, d);
        }

        public ItemsForBannanaSlugs(Item item, int a, int b, int c) {
            this(new ItemStack(item), a, b, 12, c);
        }

        public ItemsForBannanaSlugs(Item item, int a, int b, int c, int d) {
            this(new ItemStack(item), a, b, c, d);
        }

        public ItemsForBannanaSlugs(ItemStack stack, int a, int b, int c, int d) {
            this(stack, a, b, c, d, 0.05F);
        }

        public ItemsForBannanaSlugs(ItemStack stack, int a, int b, int c, int d, float e) {
            this.itemStack = stack;
            this.bugCost = a;
            this.numberOfItems = b;
            this.maxUses = c;
            this.villagerXp = d;
            this.priceMultiplier = e;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource source) {
            return new MerchantOffer(new ItemStack(BroglisBugsItems.ITEM_BANANA_SLUG.get(), this.bugCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForSnails implements BroglisBugsVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int bugCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForSnails(Block block, int a, int b, int c, int d) {
            this(new ItemStack(block), a, b, c, d);
        }

        public ItemsForSnails(Item item, int a, int b, int c) {
            this(new ItemStack(item), a, b, 12, c);
        }

        public ItemsForSnails(Item item, int a, int b, int c, int d) {
            this(new ItemStack(item), a, b, c, d);
        }

        public ItemsForSnails(ItemStack stack, int a, int b, int c, int d) {
            this(stack, a, b, c, d, 0.05F);
        }

        public ItemsForSnails(ItemStack stack, int a, int b, int c, int d, float e) {
            this.itemStack = stack;
            this.bugCost = a;
            this.numberOfItems = b;
            this.maxUses = c;
            this.villagerXp = d;
            this.priceMultiplier = e;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource source) {
            return new MerchantOffer(new ItemStack(BroglisBugsItems.ITEM_SNAIL.get(), this.bugCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }


    static class ItemsForSlugs implements BroglisBugsVillagerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int bugCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForSlugs(Block block, int a, int b, int c, int d) {
            this(new ItemStack(block), a, b, c, d);
        }

        public ItemsForSlugs(Item item, int a, int b, int c) {
            this(new ItemStack(item), a, b, 12, c);
        }

        public ItemsForSlugs(Item item, int a, int b, int c, int d) {
            this(new ItemStack(item), a, b, c, d);
        }

        public ItemsForSlugs(ItemStack stack, int a, int b, int c, int d) {
            this(stack, a, b, c, d, 0.05F);
        }

        public ItemsForSlugs(ItemStack stack, int a, int b, int c, int d, float e) {
            this.itemStack = stack;
            this.bugCost = a;
            this.numberOfItems = b;
            this.maxUses = c;
            this.villagerXp = d;
            this.priceMultiplier = e;
        }

        public MerchantOffer getOffer(Entity entity, RandomSource source) {
            return new MerchantOffer(new ItemStack(BroglisBugsItems.ITEM_SLUG.get(), this.bugCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
}

    /**public static final Int2ObjectMap<VillagerTrades.ItemListing[]> WANDERING_TRADER_TRADES = toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{
            new VillagerTrades.ItemsForEmeralds(Items.SEA_PICKLE, 2, 1, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.SLIME_BALL, 4, 1, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.GLOWSTONE, 2, 1, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.NAUTILUS_SHELL, 5, 1, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.FERN, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.SUGAR_CANE, 1, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN, 1, 1, 4, 1),
            new VillagerTrades.ItemsForEmeralds(Items.KELP, 3, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.CACTUS, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.DANDELION, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.POPPY, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BLUE_ORCHID, 1, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.ALLIUM, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.AZURE_BLUET, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.RED_TULIP, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.ORANGE_TULIP, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.WHITE_TULIP, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.PINK_TULIP, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.OXEYE_DAISY, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.CORNFLOWER, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.LILY_OF_THE_VALLEY, 1, 1, 7, 1),
            new VillagerTrades.ItemsForEmeralds(Items.WHEAT_SEEDS, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BEETROOT_SEEDS, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN_SEEDS, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.MELON_SEEDS, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.ACACIA_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BIRCH_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.DARK_OAK_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.JUNGLE_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.OAK_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.SPRUCE_SAPLING, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.MANGROVE_PROPAGULE, 5, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.RED_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.WHITE_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BLUE_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.PINK_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BLACK_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.GREEN_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.YELLOW_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.GRAY_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.PURPLE_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.LIME_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.ORANGE_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BROWN_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.CYAN_DYE, 1, 3, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BRAIN_CORAL_BLOCK, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BUBBLE_CORAL_BLOCK, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.FIRE_CORAL_BLOCK, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.HORN_CORAL_BLOCK, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.TUBE_CORAL_BLOCK, 3, 1, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.VINE, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.BROWN_MUSHROOM, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.RED_MUSHROOM, 1, 1, 12, 1),
            new VillagerTrades.ItemsForEmeralds(Items.LILY_PAD, 1, 2, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.SMALL_DRIPLEAF, 1, 2, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.SAND, 1, 8, 8, 1),
            new VillagerTrades.ItemsForEmeralds(Items.RED_SAND, 1, 4, 6, 1),
            new VillagerTrades.ItemsForEmeralds(Items.POINTED_DRIPSTONE, 1, 2, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.ROOTED_DIRT, 1, 2, 5, 1),
            new VillagerTrades.ItemsForEmeralds(Items.MOSS_BLOCK, 1, 2, 5, 1)},
            2, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.TROPICAL_FISH_BUCKET, 5, 1, 4, 1),
                    new VillagerTrades.ItemsForEmeralds(Items.PUFFERFISH_BUCKET, 5, 1, 4, 1),
                    new VillagerTrades.ItemsForEmeralds(Items.PACKED_ICE, 3, 1, 6, 1),
                    new VillagerTrades.ItemsForEmeralds(Items.BLUE_ICE, 6, 1, 6, 1),
                    new VillagerTrades.ItemsForEmeralds(Items.GUNPOWDER, 1, 1, 8, 1),
                    new VillagerTrades.ItemsForEmeralds(Items.PODZOL, 3, 3, 6, 1)}));**/
