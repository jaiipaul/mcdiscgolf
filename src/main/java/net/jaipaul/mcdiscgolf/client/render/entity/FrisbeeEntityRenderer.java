package net.jaipaul.mcdiscgolf.client.render.entity;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.client.render.entity.layer.ModModelLayers;
import net.jaipaul.mcdiscgolf.client.render.entity.model.FrisbeeEntityModel;
import net.jaipaul.mcdiscgolf.entity.FrisbeeEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class FrisbeeEntityRenderer extends EntityRenderer<FrisbeeEntity>{
    public static final Identifier TEXTURE = new Identifier(McDiscGolf.MOD_ID, "textures/item/frisbee.png");
    private final FrisbeeEntityModel model;

    public FrisbeeEntityRenderer(EntityRendererFactory.Context ctx){
        super(ctx);
        model = new FrisbeeEntityModel(ctx.getPart(ModModelLayers.FRISBEE));
    }
    
    @Override
    public void render(FrisbeeEntity frisbeeEntity, float yaw, float tickDelta,
                       MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumers, int light){
        // McDiscGolf.LOGGER.info("rendering fribsee");
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, frisbeeEntity.prevYaw, frisbeeEntity.getYaw()) - 90.0F));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, frisbeeEntity.prevPitch, frisbeeEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.model.getLayer(this.getTexture(frisbeeEntity)), false, false);
        this.model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(frisbeeEntity, yaw, tickDelta, matrixStack, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(FrisbeeEntity entity) {
        return TEXTURE;
    }
}
