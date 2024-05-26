package net.jaipaul.mcdiscgolf.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.custom.BasketBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block BASKET_BLOCK = registerBlock("basket", new BasketBlock(FabricBlockSettings.create().nonOpaque().collidable(true)), false);

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(McDiscGolf.MOD_ID, name),
        new BlockItem(block, new FabricItemSettings()));
    }

    private static Block registerBlock(String name, Block block, boolean with_block_item) {
        if(with_block_item){
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, new Identifier(McDiscGolf.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        McDiscGolf.LOGGER.info("Registering blocks for " + McDiscGolf.MOD_ID);
    }
}
