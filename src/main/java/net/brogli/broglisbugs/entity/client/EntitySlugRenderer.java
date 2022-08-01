package net.brogli.broglisbugs.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.brogli.broglisbugs.entity.variant.SlugVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class EntitySlugRenderer extends GeoEntityRenderer<EntitySlug> {

    public static final Map<SlugVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SlugVariant.class), (p_114874_) -> {
                p_114874_.put(SlugVariant.DEFAULT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/slug/entity_slug.png"));
                p_114874_.put(SlugVariant.BROWN,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/slug/entity_slug_brown.png"));
            });

    public EntitySlugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntitySlugModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntitySlug instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderType getRenderType(EntitySlug animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.35F,0.35F, 0.35F);
        } else {
            stack.scale(0.7F, 0.7F, 0.7F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    protected float getDeathMaxRotation(EntitySlug entityLivingBaseIn) {
        return 0.0F;
    }
}
