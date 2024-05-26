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
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class McDiscGolfClient implements ClientModInitializer {

    
    @Override
    public void onInitializeClient() {
        
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FRISBEE, FrisbeeEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FRISBEE_ENTITY_TYPE, FrisbeeEntityRenderer::new);
        // EntityRendererRegistry.register(ModEntities.FRISBEE_ENTITY_TYPE, (context) -> new FlyingItemEntityRenderer(context));
        HandledScreens.register(ModScreens.BASKET_SCREEN_HANDLER, BasketScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BASKET_BLOCK, RenderLayer.getCutout());
    }
}
