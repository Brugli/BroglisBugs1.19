package net.brogli.broglisbugs.entity.client;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.brogli.broglisbugs.entity.variant.SnailVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntitySnailRenderer extends GeoEntityRenderer<EntitySnail> {
    public static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SnailVariant.class), (p_114874_) -> {
                p_114874_.put(SnailVariant.DEFAULT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/snail/entity_snail.png"));
                p_114874_.put(SnailVariant.STRIPED,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/snail/entity_snail_striped.png"));
            });

    public EntitySnailRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntitySnailModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntitySnail instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderType getRenderType(EntitySnail animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4F,0.4F, 0.4F);
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
