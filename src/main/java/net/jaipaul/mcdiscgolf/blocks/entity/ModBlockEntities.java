package net.jaipaul.mcdiscgolf.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    
    public static BlockEntityType<BasketEntity> BASKET_ENTITY;

    public static void registerBlockEntities() {
        BASKET_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(McDiscGolf.MOD_ID, "basket"),
            FabricBlockEntityTypeBuilder.create(
                BasketEntity::new,
                ModBlocks.WHITE_BASKET_BLOCK,
                ModBlocks.BLACK_BASKET_BLOCK,
                ModBlocks.GRAY_BASKET_BLOCK,
                ModBlocks.LIGHT_GRAY_BASKET_BLOCK,
                ModBlocks.BROWN_BASKET_BLOCK,
                ModBlocks.PURPLE_BASKET_BLOCK,
                ModBlocks.MAGENTA_BASKET_BLOCK,
                ModBlocks.LIGHT_BLUE_BASKET_BLOCK,
                ModBlocks.BLUE_BASKET_BLOCK,
                ModBlocks.CYAN_BASKET_BLOCK,
                ModBlocks.LIME_BASKET_BLOCK,
                ModBlocks.GREEN_BASKET_BLOCK,
                ModBlocks.PINK_BASKET_BLOCK,
                ModBlocks.RED_BASKET_BLOCK,
                ModBlocks.YELLOW_BASKET_BLOCK,
                ModBlocks.ORANGE_BASKET_BLOCK
            ).build(null)
        );
    }
}
