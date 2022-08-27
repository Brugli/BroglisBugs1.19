package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityHerculesBeetle;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityHerculesBeetleModel extends AnimatedGeoModel<EntityHerculesBeetle> {

    @Override
    public ResourceLocation getModelResource(EntityHerculesBeetle object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_hercules_beetle.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityHerculesBeetle object) {
        return EntityHerculesBeetleRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(EntityHerculesBeetle animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_hercules_beetle.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(EntityHerculesBeetle entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 315F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 270F));
        }
    }
}
