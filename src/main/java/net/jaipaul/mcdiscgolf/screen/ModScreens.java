package net.jaipaul.mcdiscgolf.screen;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens {

    public static final ScreenHandlerType<BasketScreenHandler> BASKET_SCREEN_HANDLER =
        Registry.register(Registries.SCREEN_HANDLER, 
                          new Identifier(McDiscGolf.MOD_ID, "basket"),
                          new ScreenHandlerType<>(BasketScreenHandler::new, null));

    public static void registerModScreenHandlers() {
        McDiscGolf.LOGGER.info("Registering Screen Handlers for " + McDiscGolf.MOD_ID);

    }
}

