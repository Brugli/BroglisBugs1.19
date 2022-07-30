package net.brogli.broglisbugs.item.tool;

import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ItemBugNet extends TieredItem {

    public ItemBugNet(Tier tier, Item.Properties properties) {
        super(tier, properties);

    }

    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        Inventory inventory = Inventory(player);

        if (player.isHolding(this)) {
            if (entity.getType() == BroglisBugsEntityTypes.ENTITY_SLUG.get()) {
                entity.remove(Entity.RemovalReason.DISCARDED);
                //System.out.println("Slug Caught");
                {
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_SLUG.get()));
                    stack.hurtAndBreak(1, player, (p_43122_) -> {
                        p_43122_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    });
                }

            }

            if (entity.getType() == BroglisBugsEntityTypes.ENTITY_SNAIL.get()) {
                entity.remove(Entity.RemovalReason.DISCARDED);
                //System.out.println("Snail Caught");
                {
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_SNAIL.get()));
                    stack.hurtAndBreak(1, player, (p_43122_) -> {
                        p_43122_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                    });
                }

            }

        }
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return 0F;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canBeDepleted() {
        return true;
    }

    private Inventory Inventory(Player player) {
        return player.getInventory();
    }

}