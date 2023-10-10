package net.jaipaul.mcdiscgolf.entity;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {

    private static EntityType<Entity> registerEntity(String name, EntityType<Entity> entity) {
        return  Registry.register(Registries.ENTITY_TYPE, new Identifier(McDiscGolf.MOD_ID, name), entity);
    }

    public static void registerModEntities() {
        McDiscGolf.LOGGER.info("Registering mod items for " + McDiscGolf.MOD_ID);
    }
}
