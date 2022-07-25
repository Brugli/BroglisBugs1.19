package net.brogli.broglisbugs.item;

import net.brogli.broglisbugs.BroglisBugs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BroglisBugsCreativeModeTab {
    public static final CreativeModeTab BROGLISBUGS_TAB = new CreativeModeTab("broglisbugstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BroglisBugsItems.ITEM_SLUG.get());
        }
    };
}
