package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EntityStickInsectRenderer extends GeoEntityRenderer<EntityStickInsect> {

    /**public static final Map<StickInsectVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(StickInsectVariant.class), (p_114874_) -> {
                p_114874_.put(StickInsectVariant.DEFAULT,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/stickinsect/entity_stick_insect.png"));
                p_114874_.put(StickInsectVariant.DARK,
                        new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/stickinsect/entity_stick_insect_dark.png"));
            }); **/
    public EntityStickInsectRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EntityStickInsectModel());
        this.shadowRadius = 0.0f;
    }


    @Override
    public ResourceLocation getTextureLocation(EntityStickInsect instance) {
        //return LOCATION_BY_VARIANT.get(instance.getVariant());
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/stickinsect/entity_stick_insect_dark.png");
    }

    @Override
    public RenderType getRenderType(EntityStickInsect animatable, float partialTicks, PoseStack stack,
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
    protected float getDeathMaxRotation(EntityStickInsect entityLivingBaseIn) {
        return 0.0F;
    }
}
