package net.jaipaul.mcdiscgolf.item;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup MCDISCGOLF = Registry.register(
        Registries.ITEM_GROUP, 
        new Identifier(McDiscGolf.MOD_ID, "discgolf"), 
        FabricItemGroup.builder()
            .displayName(Text.translatable("itemgroup.discgolf"))
            .icon(() -> new ItemStack(ModItems.FRISBEE_ITEM))
            .entries((displayContext, entries) -> {

                entries.add(ModBlocks.WHITE_BASKET_BLOCK);
                entries.add(ModBlocks.BLACK_BASKET_BLOCK);
                entries.add(ModBlocks.GRAY_BASKET_BLOCK);
                entries.add(ModBlocks.LIGHT_GRAY_BASKET_BLOCK);
                entries.add(ModBlocks.BROWN_BASKET_BLOCK);
                entries.add(ModBlocks.PURPLE_BASKET_BLOCK);
                entries.add(ModBlocks.MAGENTA_BASKET_BLOCK);
                entries.add(ModBlocks.LIGHT_BLUE_BASKET_BLOCK);
                entries.add(ModBlocks.BLUE_BASKET_BLOCK);
                entries.add(ModBlocks.CYAN_BASKET_BLOCK);
                entries.add(ModBlocks.LIME_BASKET_BLOCK);
                entries.add(ModBlocks.GREEN_BASKET_BLOCK);
                entries.add(ModBlocks.RED_BASKET_BLOCK);
                entries.add(ModBlocks.PINK_BASKET_BLOCK);
                entries.add(ModBlocks.YELLOW_BASKET_BLOCK);
                entries.add(ModBlocks.ORANGE_BASKET_BLOCK);
                entries.add(ModItems.FRISBEE_ITEM);
            
            })
        .build());
    
    public static void registerItemGroup() {
        McDiscGolf.LOGGER.info("Registering items groups for " + McDiscGolf.MOD_ID);
    }
}
