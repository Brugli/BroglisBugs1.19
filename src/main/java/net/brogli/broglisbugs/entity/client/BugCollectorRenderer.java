package net.brogli.broglisbugs.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.BugCollector;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BugCollectorRenderer extends GeoEntityRenderer<BugCollector> {

    public BugCollectorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BugCollectorModel());
        this.shadowRadius = 0.5f;
    }


    @Override
    public ResourceLocation getTextureLocation(BugCollector instance) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/bugcollector/bug_collector.png");
    }

    @Override
    public RenderType getRenderType(BugCollector animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {

            stack.scale(0.95F, 0.95F, 0.95F);
            return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }

    @Override
    protected float getDeathMaxRotation(BugCollector entityLivingBaseIn) {
        return 90.0F;
    }
}

/**public class BugCollectorRenderer<T extends BugCollector> extends MobRenderer<T, BugCollectorModel<T>> {

    public static final ResourceLocation TEXTURE =
            new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/bugcollector/bug_collector.png");


    public BugCollectorRenderer(EntityRendererProvider.Context context) {
        super(context, new BugCollectorModel<>(context.bakeLayer(BugCollectorModel.LAYER_LOCATION)), 1.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T pEntity) { return TEXTURE; }

    @Override
    public void render(@NotNull T pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}**/
