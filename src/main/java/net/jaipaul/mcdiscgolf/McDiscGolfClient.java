package net.jaipaul.mcdiscgolf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.jaipaul.mcdiscgolf.screen.BasketScreen;
import net.jaipaul.mcdiscgolf.screen.ModScreens;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class McDiscGolfClient implements ClientModInitializer {

    
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreens.BASKET_SCREEN_HANDLER, BasketScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BASKET_BLOCK, RenderLayer.getCutout());
    }
}
