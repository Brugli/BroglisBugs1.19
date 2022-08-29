package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityBananaSlug;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntityBananaSlugRenderer extends GeoEntityRenderer<EntityBananaSlug> {

    public EntityBananaSlugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntityBananaSlugModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityBananaSlug instance) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/bananaslug/entity_banana_slug.png");
    }

    @Override
    public RenderType getRenderType(EntityBananaSlug animatable, float partialTicks, PoseStack stack,
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
    protected float getDeathMaxRotation(EntityBananaSlug entityLivingBaseIn) {
        return 0.0F;
    }
}
