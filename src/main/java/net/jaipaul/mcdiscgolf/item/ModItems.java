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
    public static final Item BASKET_ITEM = registerTallItem("basket", new TallBlockItem(ModBlocks.BASKET_BLOCK, new FabricItemSettings().maxCount(1)));
    
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
