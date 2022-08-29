package net.brogli.broglisbugs.item.tool;

import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
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

            EntityType<?> entityType = entity.getType();

            entity.remove(Entity.RemovalReason.DISCARDED);
            stack.hurtAndBreak(1, player, (p_43122_) -> {
                p_43122_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            switch (entityType.toString()) {
                case "entity.broglisbugs.entity_slug":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_SLUG.get()));
                    break;
                case "entity.broglisbugs.entity_banana_slug":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_BANANA_SLUG.get()));
                    break;
                case "entity.broglisbugs.entity_snail":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_SNAIL.get()));
                    break;
                case "entity.broglisbugs.entity_ladybird":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_LADYBIRD.get()));
                    break;
                case "entity.broglisbugs.entity_stick_insect":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_STICK_INSECT.get()));
                    break;
                case "entity.broglisbugs.entity_ant":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_ANT.get()));
                    break;
                case "entity.broglisbugs.entity_hercules_beetle":
                    inventory.add(new ItemStack(BroglisBugsItems.ITEM_HERCULES_BEETLE.get()));
                    break;
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
