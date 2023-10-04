package net.jaipaul.mcdiscgolf;

import net.fabricmc.api.ModInitializer;
import net.jaipaul.mcdiscgolf.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McDiscGolf implements ModInitializer {
	public static final String MOD_ID = "mcdiscgolf";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}