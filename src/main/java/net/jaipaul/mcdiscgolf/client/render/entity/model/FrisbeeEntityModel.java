package net.jaipaul.mcdiscgolf.client.render.entity.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;


@Environment(value=EnvType.CLIENT)
public class FrisbeeEntityModel extends Model{
    private final ModelPart root;

    public FrisbeeEntityModel(ModelPart root){
        super(RenderLayer::getEntitySolid);
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData(){
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData2 = modelPartData.addChild("center", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -1.F, -3.0F, 6.0F, 1.0F, 6.0F), ModelTransform.NONE);
        modelPartData2.addChild("rim_front", ModelPartBuilder.create().uv(0, 7).cuboid(-3.F, -1.F, -4.F, 6.0F, 1.0F, 1.0F), ModelTransform.NONE);
        modelPartData2.addChild("rim_back", ModelPartBuilder.create().uv(0, 9).cuboid(-3.F, -1.F, 3.F,  6.0F, 1.0F, 1.0F), ModelTransform.NONE);
        modelPartData2.addChild("rim_left", ModelPartBuilder.create().uv(0, 18).cuboid(3.F, -1.F, -3.0F, 1.0F, 1.0F, 6.0F), ModelTransform.NONE);
        modelPartData2.addChild("rim_right", ModelPartBuilder.create().uv(0, 11).cuboid(-4.F, -1.F, -3.0F, 1.0F, 1.0F, 6.0F), ModelTransform.NONE);

        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
