package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityLadybird;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityLadybirdModel extends AnimatedGeoModel<EntityLadybird> {

    @Override
    public ResourceLocation getModelResource(EntityLadybird object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_ladybird.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityLadybird object) {
        return EntityLadybirdRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(EntityLadybird animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_ladybird.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(EntityLadybird entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 345F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 330F));
        }
    }
}
