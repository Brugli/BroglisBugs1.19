package net.brogli.broglisbugs.item.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Animal;
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

            EntityType<?> entityType = entity.getType();

            if (entityType == BroglisBugsEntityTypes.ENTITY_SLUG.get()) {

                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_SLUG.get()));
            }

            if (entityType == BroglisBugsEntityTypes.ENTITY_BANANA_SLUG.get()) {
                System.out.println(entity.getType().getClass().getName());
                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_BANANA_SLUG.get()));
            }

            if (entityType == BroglisBugsEntityTypes.ENTITY_SNAIL.get()) {

                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_SNAIL.get()));
            }

            if (entityType == BroglisBugsEntityTypes.ENTITY_LADYBIRD.get()) {

                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_LADYBIRD.get()));
            }

            if (entityType == BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get()) {

                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_STICK_INSECT.get()));
            }

            if (entityType == BroglisBugsEntityTypes.ENTITY_ANT.get()) {

                removeAndBreakTool(entity, inventory, player, stack);
                inventory.add(new ItemStack(BroglisBugsItems.ITEM_ANT.get()));
            }
        }

        return true;
    }

    public void removeAndBreakTool(Entity entity, Inventory inventory, Player player, ItemStack stack) {
        entity.remove(Entity.RemovalReason.DISCARDED);
        stack.hurtAndBreak(1, player, (p_43122_) -> {
            p_43122_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });

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
