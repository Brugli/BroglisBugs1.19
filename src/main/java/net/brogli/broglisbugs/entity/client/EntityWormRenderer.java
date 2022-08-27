package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.brogli.broglisbugs.entity.custom.EntityWorm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntityWormRenderer extends GeoEntityRenderer<EntityWorm> {
    
    public EntityWormRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntityWormModel());
        this.shadowRadius = 0.0f;
    }
    
    @Override
    public RenderType getRenderType(EntityWorm animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.0F, 1.0F, 1.0F);  
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    protected float getDeathMaxRotation(EntityWorm entityLivingBaseIn) {
        return 0.0F;
    }

}
