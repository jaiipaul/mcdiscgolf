package net.jaipaul.mcdiscgolf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.jaipaul.mcdiscgolf.client.render.entity.FrisbeeEntityRenderer;
import net.jaipaul.mcdiscgolf.client.render.entity.layer.ModModelLayers;
import net.jaipaul.mcdiscgolf.client.render.entity.model.FrisbeeEntityModel;
import net.jaipaul.mcdiscgolf.entity.ModEntities;
import net.jaipaul.mcdiscgolf.item.ModItems;
import net.jaipaul.mcdiscgolf.screen.BasketScreen;
import net.jaipaul.mcdiscgolf.screen.ModScreens;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.recipe.RecipeSerializer;

public class McDiscGolfClient implements ClientModInitializer {

    
    @Override
    public void onInitializeClient() {
        
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FRISBEE_MODEL, FrisbeeEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FRISBEE_ENTITY_TYPE, FrisbeeEntityRenderer::new);
        HandledScreens.register(ModScreens.BASKET_SCREEN_HANDLER, BasketScreen::new);
        
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            NbtCompound nbtCompound = stack.getSubNbt("display");
            if (nbtCompound != null && nbtCompound.contains("color", NbtElement.NUMBER_TYPE)) {
                return nbtCompound.getInt("color");
            }
            return 0xFFFFFF;
        }, ModItems.FRISBEE_ITEM);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WHITE_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLACK_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAY_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_GRAY_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BROWN_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PURPLE_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAGENTA_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIGHT_BLUE_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CYAN_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GREEN_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RED_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YELLOW_BASKET_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_BASKET_BLOCK, RenderLayer.getCutout());
        
    }
}
