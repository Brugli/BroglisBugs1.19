package net.brogli.broglisbugs.entity.client;

import com.ibm.icu.text.Normalizer2;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.BugCollector;
import net.brogli.broglisbugs.entity.custom.EntityBananaSlug;
import net.brogli.broglisbugs.entity.custom.EntityStickInsect;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class BugCollectorModel extends AnimatedGeoModel<BugCollector> {

    @Override
    public ResourceLocation getModelResource(BugCollector object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "geo/bug_collector.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BugCollector object) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "textures/entity/bugcollector/bug_collector.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BugCollector animatable) {
        return new ResourceLocation(BroglisBugs.MOD_ID, "animations/bug_collector.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(BugCollector entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}

/**@OnlyIn(Dist.CLIENT)
public class BugCollectorModel<T extends BugCollector> extends HierarchicalModel<T> implements HeadedModel, VillagerHeadModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("broglisbugs", "bugcollectormodel"), "main");
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;
    protected final ModelPart nose;

    public BugCollectorModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.hat = head.getChild("Hat");
        this.nose = head.getChild("nose");
        this.rightLeg = body.getChild("RightLeg");
        this.leftLeg = body.getChild("LeftLeg");
    }

    public static LayerDefinition createBodyModel() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(88, 0).addBox(-1.5F, -13.5F, -3.5F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(31, 27).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(28, 62).addBox(-3.0F, -3.5F, -5.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(67, 71).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition Hat = head.addOrReplaceChild("Hat", CubeListBuilder.create().texOffs(0, 19).addBox(-4.5F, -14.0F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(22, 47).addBox(-3.5F, -16.5F, -3.5F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(25, 0).addBox(-1.0F, -17.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 0.0F));

        PartDefinition back_brim_r1 = Hat.addOrReplaceChild("back_brim_r1", CubeListBuilder.create().texOffs(0, 60).addBox(-4.5F, 0.0F, -1.0F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 4.5F, 0.8727F, 0.0F, 0.0F));

        PartDefinition left_brim_r1 = Hat.addOrReplaceChild("left_brim_r1", CubeListBuilder.create().texOffs(58, 8).addBox(-1.0F, 0.0F, -4.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -11.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

        PartDefinition right_brim_r1 = Hat.addOrReplaceChild("right_brim_r1", CubeListBuilder.create().texOffs(16, 58).addBox(0.0F, 0.0F, -4.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -11.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

        PartDefinition front_brim_r1 = Hat.addOrReplaceChild("front_brim_r1", CubeListBuilder.create().texOffs(60, 41).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, -4.5F, -0.8727F, 0.0F, 0.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(51, 47).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(70, 0).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(50, 67).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(57, 21).addBox(3.5F, -2.1F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(46, 56).addBox(-8.5F, -2.1F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 52).addBox(-1.0F, 5.0F, -9.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition RightLeg = body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(33, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 61).addBox(-2.5F, 8.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(28, 58).addBox(-2.5F, 10.0F, -3.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition LeftLeg = body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(60, 31).addBox(-2.5F, 8.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(-2.5F, 10.0F, -3.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition Backpack = body.addOrReplaceChild("Backpack", CubeListBuilder.create().texOffs(33, 0).addBox(-5.0F, -24.0F, 3.5F, 10.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(29, 17).addBox(-6.0F, -28.0F, 4.5F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(64, 56).addBox(-6.0F, -19.0F, 4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 70).addBox(-5.5F, -23.1F, 4.0F, 0.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition pouch_r1 = Backpack.addOrReplaceChild("pouch_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -2.0F, -1.5F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -16.5F, 5.5F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public void setupAnim(T entity, float p_104054_, float p_104055_, float p_104056_, float p_104057_, float p_104058_) {
        boolean flag = false;
        if (entity instanceof AbstractVillager) {
            flag = ((AbstractVillager) entity).getUnhappyCounter() > 0;
        }

        this.head.yRot = p_104057_ * ((float) Math.PI / 180F);
        this.head.xRot = p_104058_ * ((float) Math.PI / 180F);
        if (flag) {
            this.head.zRot = 0.3F * Mth.sin(0.45F * p_104056_);
            this.head.xRot = 0.4F;
        }

        this.rightLeg.xRot = Mth.cos(p_104054_ * 0.6662F) * 1.4F * p_104055_ * 0.5F;
        this.leftLeg.xRot = Mth.cos(p_104054_ * 0.6662F + (float) Math.PI) * 1.4F * p_104055_ * 0.5F;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void hatVisible(boolean p_104035_) {
        this.head.visible = true;
        this.hat.visible = true;
    }
}**/
