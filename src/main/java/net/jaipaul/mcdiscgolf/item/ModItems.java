package net.jaipaul.mcdiscgolf.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item FRISBEE_ITEM = registerItem("frisbee", new FrisbeeItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return  Registry.register(Registries.ITEM, new Identifier(McDiscGolf.MOD_ID, name), item);
    }
    public static void registerModItems() {
        McDiscGolf.LOGGER.info("Registering mod items for " + McDiscGolf.MOD_ID);
    }
}
