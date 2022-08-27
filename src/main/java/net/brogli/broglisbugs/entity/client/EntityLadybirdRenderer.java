package net.brogli.broglisbugs.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityLadybird;
import net.brogli.broglisbugs.entity.custom.EntitySlug;
import net.brogli.broglisbugs.entity.variant.LadybirdVariant;
import net.brogli.broglisbugs.entity.variant.SlugVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;

public class EntityLadybirdRenderer extends GeoEntityRenderer<EntityLadybird> {

    public static final Map<LadybirdVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(LadybirdVariant.class), (p_114874_) -> {
                p_114874_.put(LadybirdVariant.DEFAULT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/ladybird/entity_ladybird.png"));
                p_114874_.put(LadybirdVariant.YELLOW,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/ladybird/entity_ladybird_yellow.png"));
                p_114874_.put(LadybirdVariant.BLACK,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/ladybird/entity_ladybird_black.png"));
                p_114874_.put(LadybirdVariant.ORANGE,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/ladybird/entity_ladybird_orange.png"));
            });

    public EntityLadybirdRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntityLadybirdModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityLadybird instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderType getRenderType(EntityLadybird animatable, float partialTicks, PoseStack stack,
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
    protected float getDeathMaxRotation(EntityLadybird entityLivingBaseIn) {
        return 90.0F;
    }
}
