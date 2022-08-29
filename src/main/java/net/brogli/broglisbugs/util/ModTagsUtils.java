package net.brogli.broglisbugs.util;

import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTagsUtils {

    public static boolean isMemberOfEntityTag(TagKey<EntityType<?>> tag, Entity entity){
        return Registry.ENTITY_TYPE.getHolderOrThrow(Registry.ENTITY_TYPE.getResourceKey(entity.getType()).get()).is(tag);
    }

    public static boolean isMemberOfItemTag(TagKey<Item> tag, Item item){
        return Registry.ITEM.getHolderOrThrow(Registry.ITEM.getResourceKey(item).get()).is(tag);
    }

    public static boolean isMemberOfBlockTag(TagKey<Block> tag, Block block){
        return Registry.BLOCK.getHolderOrThrow(Registry.BLOCK.getResourceKey(block).get()).is(tag);
    }
    
}
