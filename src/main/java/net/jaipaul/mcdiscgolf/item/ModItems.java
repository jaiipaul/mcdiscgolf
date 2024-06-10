package net.jaipaul.mcdiscgolf.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.minecraft.item.Item;
import net.minecraft.item.TallBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item FRISBEE_ITEM = registerItem("frisbee", new FrisbeeItem(new FabricItemSettings().maxCount(1)));
    public static final Item WHITE_BASKET_ITEM = registerTallItem("white_basket", new TallBlockItem(ModBlocks.WHITE_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item BLACK_BASKET_ITEM = registerTallItem("black_basket", new TallBlockItem(ModBlocks.BLACK_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item GRAY_BASKET_ITEM = registerTallItem("gray_basket", new TallBlockItem(ModBlocks.GRAY_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item LIGHT_GRAY_BASKET_ITEM = registerTallItem("light_gray_basket", new TallBlockItem(ModBlocks.LIGHT_GRAY_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item BROWN_BASKET_ITEM = registerTallItem("brown_basket", new TallBlockItem(ModBlocks.BROWN_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item PURPLE_BASKET_ITEM = registerTallItem("purple_basket", new TallBlockItem(ModBlocks.PURPLE_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item MAGENTA_BASKET_ITEM = registerTallItem("magenta_basket", new TallBlockItem(ModBlocks.MAGENTA_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item LIGHT_BLUE_BASKET_ITEM = registerTallItem("light_blue_basket", new TallBlockItem(ModBlocks.LIGHT_BLUE_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item BLUE_BASKET_ITEM = registerTallItem("blue_basket", new TallBlockItem(ModBlocks.BLUE_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item CYAN_BASKET_ITEM = registerTallItem("cyan_basket", new TallBlockItem(ModBlocks.CYAN_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item LIME_BASKET_ITEM = registerTallItem("lime_basket", new TallBlockItem(ModBlocks.LIME_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item GREEN_BASKET_ITEM = registerTallItem("green_basket", new TallBlockItem(ModBlocks.GREEN_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item PINK_BASKET_ITEM = registerTallItem("pink_basket", new TallBlockItem(ModBlocks.PINK_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item RED_BASKET_ITEM = registerTallItem("red_basket", new TallBlockItem(ModBlocks.RED_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item YELLOW_BASKET_ITEM = registerTallItem("yellow_basket", new TallBlockItem(ModBlocks.YELLOW_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    public static final Item ORANGE_BASKET_ITEM = registerTallItem("orange_basket", new TallBlockItem(ModBlocks.ORANGE_BASKET_BLOCK, new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return  Registry.register(Registries.ITEM, new Identifier(McDiscGolf.MOD_ID, name), item);
    }

    private static Item registerTallItem(String name, TallBlockItem item) {
        return  Registry.register(Registries.ITEM, new Identifier(McDiscGolf.MOD_ID, name), item);
    }

    public static void registerModItems() {
        McDiscGolf.LOGGER.info("Registering mod items for " + McDiscGolf.MOD_ID);
    }
}
