package net.brogli.broglisbugs.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityHerculesBeetle;
import net.brogli.broglisbugs.entity.custom.EntitySnail;
import net.brogli.broglisbugs.entity.variant.HerculesBeetleVariant;
import net.brogli.broglisbugs.entity.variant.SnailVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class EntityHerculesBeetleRenderer extends GeoEntityRenderer<EntityHerculesBeetle> {
    public static final Map<HerculesBeetleVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(HerculesBeetleVariant.class), (p_114874_) -> {
                p_114874_.put(HerculesBeetleVariant.DEFAULT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/herculesbeetle/hercules_beetle_dark.png"));
                p_114874_.put(HerculesBeetleVariant.LIGHT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/herculesbeetle/hercules_beetle.png"));
            });

    public EntityHerculesBeetleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntityHerculesBeetleModel());
        this.shadowRadius = 0.4f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityHerculesBeetle instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderType getRenderType(EntityHerculesBeetle animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.6F,0.6F, 0.6F);
        } else {
            stack.scale(1.0F, 1.0F, 1.0F);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    protected float getDeathMaxRotation(EntityHerculesBeetle entityLivingBaseIn) {
        return 180.0F;
    }
}
