package net.brogli.broglisbugs.entity.custom;

import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntitySlug extends Animal implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public EntitySlug(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    //Attributes
    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.14F)
                .add(Attributes.FOLLOW_RANGE, 32.0D).build();
    }

    //Goals
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1,new TemptGoal(this, 1.25D, Ingredient.of(Items.RED_MUSHROOM), false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        EntitySlug baby = BroglisBugsEntityTypes.ENTITY_SLUG.get().create(serverLevel);
        return baby;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == Items.RED_MUSHROOM;
    }

    //Animation
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entityslug.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entityslug.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    //Sounds
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SLIME_SQUISH, 0.1F, 1.0F);
    }

    //Interaction
    public int cooldown = 0;

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (itemstack.is(Items.GLASS_BOTTLE) && !this.isBaby()) {
            if (cooldown == 0) {
                player.playSound(SoundEvents.SLIME_SQUISH, 1.0F, 1.0F);
                ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, player,
                        BroglisBugsItems.ITEM_SLIME_BOTTLE.get().getDefaultInstance());
                player.setItemInHand(hand, itemstack1);
                // 1 minute cooldown = 1200
                cooldown = 3000;
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        }
        return super.mobInteract(player, hand);
    }

    public void tick() {
        if (cooldown > 0) {
            cooldown--;

        }
        super.tick();
    }
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
                                        MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable CompoundTag tag) {

        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, tag);
    }
}
