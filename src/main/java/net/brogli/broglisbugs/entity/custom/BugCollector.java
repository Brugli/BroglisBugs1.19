package net.brogli.broglisbugs.entity.custom;

import com.google.common.collect.Sets;
import net.brogli.broglisbugs.villager.BroglisBugsVillagerTrades;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Set;

public class BugCollector extends WanderingTrader implements IAnimatable {

    AnimationFactory factory = new AnimationFactory(this);

    private int despawnDelay;

    public BugCollector(EntityType<? extends BugCollector> entityType, Level level) {
        super(entityType, level );
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5F)
                .add(Attributes.FOLLOW_RANGE, 48.0D).build();


    }

    protected void addOffersFromItemListings(MerchantOffers p_35278_, BroglisBugsVillagerTrades.ItemListing[] listings, int a) {
        Set<Integer> set = Sets.newHashSet();
        if (listings.length > a) {
            while(set.size() < a) {
                set.add(this.random.nextInt(listings.length));
            }
        } else {
            for(int i = 0; i < listings.length; ++i) {
                set.add(i);
            }
        }

        for(Integer integer : set) {
            BroglisBugsVillagerTrades.ItemListing villagertrades$itemlisting = listings[integer];
            MerchantOffer merchantoffer = villagertrades$itemlisting.getOffer(this, this.random);
            if (merchantoffer != null) {
                p_35278_.add(merchantoffer);
            }
        }

    }


    protected void updateTrades() {
        BroglisBugsVillagerTrades.ItemListing[] abroglisbugsvillagertrades$itemlisting = BroglisBugsVillagerTrades.BUG_COLLECTOR_TRADES.get(1);
        BroglisBugsVillagerTrades.ItemListing[] abroglisbugsvillagertrades$itemlisting1 = BroglisBugsVillagerTrades.BUG_COLLECTOR_TRADES.get(2);
        BroglisBugsVillagerTrades.ItemListing[] abroglisbugsvillagertrades$itemlisting2 = BroglisBugsVillagerTrades.BUG_COLLECTOR_TRADES.get(3);
        if (abroglisbugsvillagertrades$itemlisting != null && abroglisbugsvillagertrades$itemlisting1 != null && abroglisbugsvillagertrades$itemlisting2 !=null) {
            MerchantOffers merchantoffers = this.getOffers();
            this.addOffersFromItemListings(merchantoffers, abroglisbugsvillagertrades$itemlisting, 1);
            this.addOffersFromItemListings(merchantoffers, abroglisbugsvillagertrades$itemlisting1, 1);
            this.addOffersFromItemListings(merchantoffers, abroglisbugsvillagertrades$itemlisting2, 1);

        }
    }

    public void addAdditionalSaveData(CompoundTag p_35861_) {
        super.addAdditionalSaveData(p_35861_);
        p_35861_.putInt("DespawnDelay", this.despawnDelay);
    }

    public void readAdditionalSaveData(CompoundTag p_35852_) {
        super.readAdditionalSaveData(p_35852_);
        if (p_35852_.contains("DespawnDelay", 99)) {
            this.despawnDelay = p_35852_.getInt("DespawnDelay");
        }
        this.setAge(Math.max(0, this.getAge()));
    }

    public void setDespawnDelay(int p_35892_) {
        this.despawnDelay = p_35892_;
    }

    public int getDespawnDelay() {
        return this.despawnDelay;
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.maybeDespawn();
        }
    }

    private void maybeDespawn() {
        if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
            this.discard();
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bug_collector.walking", true));
            System.out.println("bug collector walking");
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bug_collector.idle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


}
