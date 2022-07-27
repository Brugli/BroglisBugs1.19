package net.brogli.broglisbugs.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum BroglisBugsTiers implements Tier {

    BUG_NET(0, 50, 15F, 0F, 0, () ->
    { return Ingredient.of(BroglisBugsItems.ITEM_NETTING.get());
    });

    private final int level, uses, enchantment;
    private final float speed, damage;
    private final Supplier<Ingredient> repairMaterial;

    BroglisBugsTiers(int level, int uses, float speed, float damage, int enchantment, Supplier<Ingredient> repairMaterial) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantment = enchantment;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantment;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }

}
