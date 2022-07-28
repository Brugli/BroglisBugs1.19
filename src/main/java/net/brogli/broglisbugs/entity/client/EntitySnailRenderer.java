package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntitySnailRenderer extends GeoEntityRenderer<EntitySnail> {

    public EntitySnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntitySnailModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntitySnail instance) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/snail/entity_snail.png");
    }

    @Override
    public RenderType getRenderType(EntitySnail animatable, float partialTicks, PoseStack stack,
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
    protected float getDeathMaxRotation(EntitySnail entityLivingBaseIn) {
        return 0.0F;
    }
}
