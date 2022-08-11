package net.brogli.broglisbugs.entity.custom;

import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.variant.SlugVariant;
import net.brogli.broglisbugs.entity.variant.SnailVariant;
import net.brogli.broglisbugs.entity.variant.StickInsectVariant;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.level.gameevent.GameEvent;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntityStickInsect extends Animal implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    /**private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(EntityStickInsect.class, EntityDataSerializers.INT); **/
    public EntityStickInsect(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }


    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.14F)
                .add(Attributes.FOLLOW_RANGE, 32.0D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

    }

    public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
        return false;
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        EntityStickInsect baby = BroglisBugsEntityTypes.ENTITY_STICK_INSECT.get().create(serverLevel);
        //StickInsectVariant variant = Util.getRandom(StickInsectVariant.values(), this.random);
        //baby.setVariant(variant);
        return baby;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == Items.APPLE;
    }

    //Animation
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entity_stick_insect.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entity_stick_insect.idle", true));
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
        this.playSound(SoundEvents.SILVERFISH_STEP, 0.05F, 1.0F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        //this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        //tag.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        //this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public boolean canBeLeashed(Player player) {
        return false;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
                                        MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable CompoundTag tag) {
        //StickInsectVariant variant = Util.getRandom(StickInsectVariant.values(), this.random);
        //setVariant(variant);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, tag);
    }

    /** public StickInsectVariant getVariant() {
        return StickInsectVariant.byId(this.getTypeVariant() & 255);
    } **/

    /** public int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    } **/

    /** public void setVariant(StickInsectVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    } **/


}
