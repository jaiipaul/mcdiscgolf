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

    public static final Block WHITE_BASKET_BLOCK = registerBlock("white_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block BLACK_BASKET_BLOCK = registerBlock("black_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block GRAY_BASKET_BLOCK = registerBlock("gray_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block LIGHT_GRAY_BASKET_BLOCK = registerBlock("light_gray_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block BROWN_BASKET_BLOCK = registerBlock("brown_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block PURPLE_BASKET_BLOCK = registerBlock("purple_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block MAGENTA_BASKET_BLOCK = registerBlock("magenta_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block LIGHT_BLUE_BASKET_BLOCK = registerBlock("light_blue_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block BLUE_BASKET_BLOCK = registerBlock("blue_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block CYAN_BASKET_BLOCK = registerBlock("cyan_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block LIME_BASKET_BLOCK = registerBlock("lime_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block GREEN_BASKET_BLOCK = registerBlock("green_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block PINK_BASKET_BLOCK = registerBlock("pink_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block RED_BASKET_BLOCK = registerBlock("red_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block YELLOW_BASKET_BLOCK = registerBlock("yellow_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);
    public static final Block ORANGE_BASKET_BLOCK = registerBlock("orange_basket", new BasketBlock(FabricBlockSettings.create().requiresTool().strength(4.0f, 5.0f).nonOpaque().collidable(true)), false);

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
