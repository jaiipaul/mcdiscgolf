package net.jaipaul.mcdiscgolf.item;
import net.jaipaul.mcdiscgolf.entity.FrisbeeEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class FrisbeeItem extends Item {
    
    public FrisbeeItem(Item.Settings settings){
        super(settings);
    }
    
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    public static float getPullProgress(int useTicks) {
      float f = (float)useTicks / 20.0F;
      f = (f * f + f * 2.0F) / 3.0F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
      if (!(user instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity playerEntity = (PlayerEntity)user;
      if (!world.isClient) {
          FrisbeeEntity frisbeeEntity = new FrisbeeEntity(world, playerEntity, stack);
          int i = this.getMaxUseTime(stack) - remainingUseTicks;
          float f = getPullProgress(i);

          frisbeeEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 0.5F + f * 0.5F, 1.0F);
          if (playerEntity.getAbilities().creativeMode) {
            frisbeeEntity.pickupType = PickupPermission.CREATIVE_ONLY;
          }
          world.playSound((PlayerEntity)null, user.getBlockPos(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
          world.spawnEntity(frisbeeEntity);

          if (!playerEntity.getAbilities().creativeMode) {
            playerEntity.getInventory().removeOne(stack);
          }
      }
   }

   public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
      ItemStack itemStack = user.getStackInHand(hand);
      user.setCurrentHand(hand);
      return TypedActionResult.consume(itemStack);
   }
}
