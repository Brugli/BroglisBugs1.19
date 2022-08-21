package net.brogli.broglisbugs.block;

import net.brogli.broglisbugs.BroglisBugs;
import net.brogli.broglisbugs.block.custom.BlockSalt;
import net.brogli.broglisbugs.item.BroglisBugsCreativeModeTab;
import net.brogli.broglisbugs.item.BroglisBugsItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BroglisBugsBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BroglisBugs.MOD_ID);

    public static final RegistryObject<BlockSalt> BLOCK_SALT = registerBlock("block_salt",
            () -> new BlockSalt(BlockBehaviour.Properties.of(Material.PLANT)
                    .strength(0f).requiresCorrectToolForDrops()), BroglisBugsCreativeModeTab.BROGLISBUGS_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return BroglisBugsItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}