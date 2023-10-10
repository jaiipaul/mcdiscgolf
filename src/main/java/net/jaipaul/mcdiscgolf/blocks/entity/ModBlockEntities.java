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
            FabricBlockEntityTypeBuilder.create(BasketEntity::new, ModBlocks.BASKET_BLOCK).build(null)
        );
    }
}
