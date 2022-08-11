package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityBananaSlug;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityStickInsectModel extends AnimatedGeoModel<EntityStickInsect> {

    @Override
    public ResourceLocation getModelResource(EntityStickInsect object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_stick_insect.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityStickInsect object) {
        //return EntityStickInsectRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/stickinsect/entity_stick_insect_dark.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityStickInsect animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_stick_insect.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(EntityStickInsect entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 345F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 350F));
        }
    }
}
