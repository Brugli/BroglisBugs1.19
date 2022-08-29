package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityWorm;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityWormModel extends AnimatedGeoModel<EntityWorm>{

    
    @Override
    public ResourceLocation getModelResource(EntityWorm object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_worm.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityWorm object) {
        return new ResourceLocation(BroglisBugs.MOD_ID,"textures/entity/worm/entity_worm.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityWorm animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_worm.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(EntityWorm entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Main");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 315F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 270F));
        }
    }
    
}
