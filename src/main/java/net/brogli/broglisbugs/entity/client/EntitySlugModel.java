package net.brogli.broglisbugs.entity.client;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EntitySlugModel extends AnimatedGeoModel<EntitySlug> {

    @Override
    public ResourceLocation getModelResource(EntitySlug object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/entity_slug.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySlug object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/slug/entity_slug.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySlug animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/entity_slug.animation.json");
    }
}
