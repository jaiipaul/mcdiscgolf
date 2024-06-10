package net.jaipaul.mcdiscgolf.recipe;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static RecipeSerializer<FrisbeeDyeRecipe> FRISBEE_DYE;

    public static void registerModeRecipes() {
        McDiscGolf.LOGGER.info("Registering mod recipe for " + McDiscGolf.MOD_ID);

        FRISBEE_DYE = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(McDiscGolf.MOD_ID, "frisbee_dye"), new SpecialRecipeSerializer<FrisbeeDyeRecipe>(FrisbeeDyeRecipe::new));
    }
}
