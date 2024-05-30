package net.jaipaul.mcdiscgolf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.jaipaul.mcdiscgolf.client.render.entity.FrisbeeEntityRenderer;
import net.jaipaul.mcdiscgolf.client.render.entity.layer.ModModelLayers;
import net.jaipaul.mcdiscgolf.client.render.entity.model.FrisbeeEntityModel;
import net.jaipaul.mcdiscgolf.entity.ModEntities;
import net.jaipaul.mcdiscgolf.screen.BasketScreen;
import net.jaipaul.mcdiscgolf.screen.ModScreens;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class McDiscGolfClient implements ClientModInitializer {

    
    @Override
    public void onInitializeClient() {
        
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FRISBEE, FrisbeeEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FRISBEE_ENTITY_TYPE, FrisbeeEntityRenderer::new);
        // EntityRendererRegistry.register(ModEntities.FRISBEE_ENTITY_TYPE, (context) -> new FlyingItemEntityRenderer(context));
        HandledScreens.register(ModScreens.BASKET_SCREEN_HANDLER, BasketScreen::new);

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
