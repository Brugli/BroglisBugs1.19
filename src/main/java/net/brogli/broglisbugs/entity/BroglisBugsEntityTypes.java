package net.brogli.broglisbugs.entity;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisBugsEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BroglisBugs.MOD_ID);

    public static final RegistryObject<EntityType<EntitySlug>> ENTITY_SLUG =
            ENTITY_TYPES.register("entity_slug",
                    () -> EntityType.Builder.of(EntitySlug::new, MobCategory.CREATURE)
                            .clientTrackingRange(9)
                            .sized(0.75f, 0.5f)
                            .build(new ResourceLocation(BroglisBugs.MOD_ID, "entity_slug").toString()));

    public static final RegistryObject<EntityType<EntityBananaSlug>> ENTITY_BANANA_SLUG =
            ENTITY_TYPES.register("entity_banana_slug",
                    () -> EntityType.Builder.of(EntityBananaSlug::new, MobCategory.CREATURE)
                            .clientTrackingRange(9)
                            .sized(0.75f, 0.5f)
                            .build(new ResourceLocation(BroglisBugs.MOD_ID, "entity_banana_slug").toString()));

    public static final RegistryObject<EntityType<EntitySnail>> ENTITY_SNAIL =
            ENTITY_TYPES.register("entity_snail",
                    () -> EntityType.Builder.of(EntitySnail::new, MobCategory.CREATURE)
                            .clientTrackingRange(9)
                            .sized(0.75f, 0.5f)
                            .build(new ResourceLocation(BroglisBugs.MOD_ID, "entity_snail").toString()));

    public static final RegistryObject<EntityType<EntityLadybird>> ENTITY_LADYBIRD =
            ENTITY_TYPES.register("entity_ladybird",
                    () -> EntityType.Builder.of(EntityLadybird::new, MobCategory.CREATURE)
                            .clientTrackingRange(9)
                            .sized(0.75f, 0.5f)
                            .build(new ResourceLocation(BroglisBugs.MOD_ID, "entity_ladybird").toString()));

    public static final RegistryObject<EntityType<EntityStickInsect>> ENTITY_STICK_INSECT =
            ENTITY_TYPES.register("entity_stick_insect",
                    () -> EntityType.Builder.of(EntityStickInsect::new, MobCategory.CREATURE)
                            .clientTrackingRange(9)
                            .sized(0.75f, 0.5f)
                            .build(new ResourceLocation(BroglisBugs.MOD_ID, "entity_stick_insect").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
