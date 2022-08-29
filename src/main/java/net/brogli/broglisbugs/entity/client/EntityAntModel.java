package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityAnt;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class EntityAntModel extends AnimatedGeoModel<EntityAnt> {

    @Override
    public ResourceLocation getModelResource(EntityAnt object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_ant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityAnt object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/ant/entity_ant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityAnt animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_ant.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(EntityAnt entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 345F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 350F));
        }
    }
}
