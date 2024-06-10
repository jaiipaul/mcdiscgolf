package net.jaipaul.mcdiscgolf.client.render.entity;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.jaipaul.mcdiscgolf.client.render.entity.layer.ModModelLayers;
import net.jaipaul.mcdiscgolf.client.render.entity.model.FrisbeeEntityModel;
import net.jaipaul.mcdiscgolf.entity.FrisbeeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(value=EnvType.CLIENT)
public class FrisbeeEntityRenderer extends EntityRenderer<FrisbeeEntity>{
    private static final Identifier TEXTURE = new Identifier(McDiscGolf.MOD_ID, "textures/entity/frisbee.png");
    private final FrisbeeEntityModel model;

    public FrisbeeEntityRenderer(EntityRendererFactory.Context ctx){
        super(ctx);
        model = new FrisbeeEntityModel(ctx.getPart(ModModelLayers.FRISBEE_MODEL));
    }
    
    @Override
    public void render(FrisbeeEntity frisbeeEntity, float yaw, float tickDelta,
                       MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumers, int light){

        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, frisbeeEntity.prevYaw, frisbeeEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, frisbeeEntity.prevPitch, frisbeeEntity.getPitch()) + 180.f));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(frisbeeEntity)));
        
        int i = frisbeeEntity.getColor();
        float r = (float)(i >> 16 & 0xFF) / 255.0f;
        float g = (float)(i >> 8 & 0xFF) / 255.0f;
        float b = (float)(i & 0xFF) / 255.0f;
        this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, r, g, b, 1.0F);
        matrixStack.pop();
        super.render(frisbeeEntity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    
    @Override
    public Identifier getTexture(FrisbeeEntity entity) {
        return TEXTURE;
    }
}
