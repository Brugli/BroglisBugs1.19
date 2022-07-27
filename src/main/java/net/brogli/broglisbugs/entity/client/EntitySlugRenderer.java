package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntitySlugRenderer extends GeoEntityRenderer<EntitySlug> {

    public EntitySlugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntitySlugModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntitySlug instance) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/slug/entity_slug.png");
    }

    @Override
    public RenderType getRenderType(EntitySlug animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.8F,0.8F, 0.8F);
        } else {
            stack.scale(1.25F, 1.25F, 1.25F);
        }
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    protected float getDeathMaxRotation(EntitySlug entityLivingBaseIn) {
        return 0.0F;
    }
}
