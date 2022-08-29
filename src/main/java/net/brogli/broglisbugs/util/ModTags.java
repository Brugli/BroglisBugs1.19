package net.brogli.broglisbugs.util;

import net.minecraftforge.common.Tags;
import net.brogli.broglisbugs.BroglisBugs;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(BroglisBugs.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Items {

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(BroglisBugs.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name){
            return ItemTags.create(new ResourceLocation("forge", name));
        }

    }

    public static class Entities{

        public static final TagKey<EntityType<?>> NETTABLE_ENTITIES = tag("nettable_entities");
        
        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(BroglisBugs.MOD_ID, name));
        }

        private static TagKey<EntityType<?>> forgeTag(String name){
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation("forge", name));
        }
    }
    
}
