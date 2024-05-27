package net.jaipaul.mcdiscgolf.sounds;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    
    public static final Identifier BASKET_CHAINS_SOUND = new Identifier("mcdiscgolf:block.basket.chains");
    public static final Identifier BASKET_HIT_SOUND = new Identifier("mcdiscgolf:block.basket.hit");
    
    public static SoundEvent BASKET_CHAINS = SoundEvent.of(BASKET_CHAINS_SOUND);
    public static SoundEvent BASKET_HIT = SoundEvent.of(BASKET_HIT_SOUND);
    
    public static void registerModSounds() {
        McDiscGolf.LOGGER.info("Registering mod sournds for " + McDiscGolf.MOD_ID);
        Registry.register(Registries.SOUND_EVENT, BASKET_CHAINS_SOUND, BASKET_CHAINS);
        Registry.register(Registries.SOUND_EVENT, BASKET_HIT_SOUND, BASKET_HIT);
    }
}
