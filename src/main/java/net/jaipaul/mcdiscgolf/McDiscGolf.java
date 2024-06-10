package net.jaipaul.mcdiscgolf;

import net.fabricmc.api.ModInitializer;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.jaipaul.mcdiscgolf.blocks.entity.ModBlockEntities;
import net.jaipaul.mcdiscgolf.entity.ModEntities;
import net.jaipaul.mcdiscgolf.item.ModItemGroup;
import net.jaipaul.mcdiscgolf.item.ModItems;
import net.jaipaul.mcdiscgolf.recipe.ModRecipes;
import net.jaipaul.mcdiscgolf.screen.ModScreens;
import net.jaipaul.mcdiscgolf.sounds.ModSounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McDiscGolf implements ModInitializer {
	public static final String MOD_ID = "mcdiscgolf";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		ModItemGroup.registerItemGroup();
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreens.registerModScreenHandlers();
		ModSounds.registerModSounds();
		ModRecipes.registerModeRecipes();
	}
}