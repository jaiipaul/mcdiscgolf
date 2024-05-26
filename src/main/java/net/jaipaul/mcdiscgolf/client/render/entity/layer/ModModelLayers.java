package net.jaipaul.mcdiscgolf.client.render.entity.layer;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer FRISBEE =
            new EntityModelLayer(new Identifier(McDiscGolf.MOD_ID, "frisbee"), "main"); 
}
