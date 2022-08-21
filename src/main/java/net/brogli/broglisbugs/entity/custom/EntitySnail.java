package net.brogli.broglisbugs.entity.custom;

import net.brogli.broglisbugs.entity.BroglisBugsEntityTypes;
import net.brogli.broglisbugs.entity.variant.SnailVariant;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EntitySnail extends Animal implements IAnimatable {

    int moreCropTicks;
    private AnimationFactory factory = new AnimationFactory(this);

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(EntitySnail.class, EntityDataSerializers.INT);

    public EntitySnail(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new EntitySnail.EntitySnailMoveControl(this);
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
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.BROWN_MUSHROOM), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new EntitySnail.RaidGardenGoal(this));

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob mob) {
        EntitySnail baby = BroglisBugsEntityTypes.ENTITY_SNAIL.get().create(serverLevel);
        SnailVariant variant = Util.getRandom(SnailVariant.values(), this.random);
        baby.setVariant(variant);
        return baby;
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.getItem() == Items.BROWN_MUSHROOM;
    }

    //Animation
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entity_snail_walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.entity_snail_idle", true));
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
    @Override
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

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.entityData.set(DATA_ID_TYPE_VARIANT, tag.getInt("Variant"));
        this.moreCropTicks = tag.getInt("MoreCropTicks");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getTypeVariant());
        tag.putInt("MoreCropTicks", this.moreCropTicks);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public boolean canBeLeashed(Player player) {
        return false;
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
                                        MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable CompoundTag tag) {
        SnailVariant variant = Util.getRandom(SnailVariant.values(), this.random);
        setVariant(variant);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, tag);
    }

    public SnailVariant getVariant() {
        return SnailVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(SnailVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    public void customServerAiStep() {

        if (this.moreCropTicks > 0) {
            this.moreCropTicks -= this.random.nextInt(3);
            if (this.moreCropTicks < 0) {
                this.moreCropTicks = 0;
            }
        }
    }

    boolean wantsMoreFood() {
        return this.moreCropTicks == 0;
    }

    static class EntitySnailMoveControl extends MoveControl {
        private final EntitySnail entitySnail;

        public EntitySnailMoveControl(EntitySnail entitySnail) {
            super(entitySnail);
            this.entitySnail = entitySnail;
        }

        public void setWantedPosition(double p_29769_, double p_29770_, double p_29771_, double p_29772_) {
            super.setWantedPosition(p_29769_, p_29770_, p_29771_, p_29772_);
        }
    }

    static class RaidGardenGoal extends MoveToBlockGoal {
        private final EntitySnail entitySnail;
        private boolean wantsToRaid;
        private boolean canRaid;

        public RaidGardenGoal(EntitySnail entitySnail) {
            super(entitySnail, (double)0.7F, 16);
            this.entitySnail = entitySnail;
        }

        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.entitySnail.level, this.entitySnail)) {
                    return false;
                }

                this.canRaid = false;
                this.wantsToRaid = this.entitySnail.wantsMoreFood();
                this.wantsToRaid = true;
            }

            return super.canUse();
        }

        public boolean canContinueToUse() {
            return this.canRaid && super.canContinueToUse();
        }

        public void tick() {
            super.tick();
            this.entitySnail.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.entitySnail.getMaxHeadXRot());
            if (this.isReachedTarget()) {
                Level level = this.entitySnail.level;
                BlockPos blockpos = this.blockPos.above();
                BlockState blockstate = level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (this.canRaid && block instanceof CropBlock) {
                    level.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);
                    level.destroyBlock(blockpos, true, this.entitySnail);
                    this.entitySnail.moreCropTicks = 40;
                    this.entitySnail.playSound(SoundEvents.CROP_BREAK, 1.0F, 1.0F);
                    System.out.println("Crops Eaten");
                }

                this.canRaid = false;
                this.nextStartTick = 10;
            }

        }

        protected boolean isValidTarget(LevelReader level, BlockPos pos) {
            BlockState blockstate = level.getBlockState(pos);
            if (blockstate.is(Blocks.FARMLAND) && this.wantsToRaid && !this.canRaid) {
                blockstate = level.getBlockState(pos.above());
                if (blockstate.getBlock() instanceof CropBlock && ((CropBlock)blockstate.getBlock()).isMaxAge(blockstate)) {
                    this.canRaid = true;
                    return true;
                }
            }

            return false;
        }
    }
}

