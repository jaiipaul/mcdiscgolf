package net.jaipaul.mcdiscgolf.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModEntities {

    public static final EntityType<FrisbeeEntity> FRISBEE_ENTITY_TYPE = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(McDiscGolf.MOD_ID, "frisbee"),
        FabricEntityTypeBuilder.<FrisbeeEntity>create(SpawnGroup.MISC, FrisbeeEntity::new)
            .dimensions(EntityDimensions.fixed(0.0625f, 0.0625f))
            .build());

    public static void registerModEntities() {
        McDiscGolf.LOGGER.info("Registering mod entities for " + McDiscGolf.MOD_ID);
    }
    
}
