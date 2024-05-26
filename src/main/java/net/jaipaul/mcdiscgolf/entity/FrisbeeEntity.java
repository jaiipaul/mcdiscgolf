package net.jaipaul.mcdiscgolf.entity;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.ModBlocks;
import net.jaipaul.mcdiscgolf.blocks.custom.BasketBlock;
import net.jaipaul.mcdiscgolf.blocks.entity.BasketEntity;
import net.jaipaul.mcdiscgolf.blocks.entity.ModBlockEntities;
import net.jaipaul.mcdiscgolf.item.FrisbeeItem;
import net.jaipaul.mcdiscgolf.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;


public class FrisbeeEntity extends PersistentProjectileEntity  {
    private ItemStack frisbeeStack;

    // public FrisbeeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
	// 	super(entityType, world);
	// }
 
	// public FrisbeeEntity(World world, LivingEntity owner) {
	// 	super(ModEntities.FRISBEE_ENTITY_TYPE, owner, world); // null will be changed later
	// }
 
	// public FrisbeeEntity(World world, double x, double y, double z) {
	// 	super(ModEntities.FRISBEE_ENTITY_TYPE, x, y, z, world); // null will be changed later
	// }
 
	// @Override
	// protected Item getDefaultItem() {
	// 	return ModItems.FRISBEE_ITEM; // We will configure this later, once we have created the ProjectileItem.
	// }

    // @Override
    // public Packet<ClientPlayPacketListener> createSpawnPacket() {
    //     return new EntitySpawnS2CPacket(this);
    // }

    public FrisbeeEntity(EntityType<? extends FrisbeeEntity> entityType, World world) {
        super(entityType, world);
        this.frisbeeStack = new ItemStack(ModItems.FRISBEE_ITEM);
    }

    public FrisbeeEntity(World world, PlayerEntity player, ItemStack stack) {
        super(ModEntities.FRISBEE_ENTITY_TYPE, player, world);
        this.frisbeeStack = new ItemStack(ModItems.FRISBEE_ITEM);
        this.frisbeeStack = stack.copy();
    }

    protected ItemStack asItemStack() {
        return this.frisbeeStack.copy();
    }

    public ItemStack getStack() {
        return this.frisbeeStack.copy();
    }

	// protected Item getDefaultItem() {
    // 		return ModItems.FRISBEE_ITEM;
 	// }

    // @Override
    // public void tick() {
    //     Vec3d vec3d2;
    //     VoxelShape voxelShape;
    //     super.tick();
    //     boolean bl = this.isNoClip();
    //     Vec3d vec3d = this.getVelocity();
    //     if (this.prevPitch == 0.0f && this.prevYaw == 0.0f) {
    //         double d = vec3d.horizontalLength();
    //         this.setYaw((float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875));
    //         this.setPitch((float)(MathHelper.atan2(vec3d.y, d) * 57.2957763671875));
    //         this.prevYaw = this.getYaw();
    //         this.prevPitch = this.getPitch();
    //     }
    //     BlockPos blockPos = this.getBlockPos();
    //     BlockState blockState = this.getWorld().getBlockState(blockPos);
    //     if (!(blockState.isAir() || bl || (voxelShape = blockState.getCollisionShape(this.getWorld(), blockPos)).isEmpty())) {
    //         vec3d2 = this.getPos();
    //         for (Box box : voxelShape.getBoundingBoxes()) {
    //             if (!box.offset(blockPos).contains(vec3d2)) continue;
    //             this.inGround = true;
    //             break;
    //         }
    //     }
    //     if (this.shake > 0) {
    //         --this.shake;
    //     }
    //     if (this.isTouchingWaterOrRain() || blockState.isOf(Blocks.POWDER_SNOW)) {
    //         this.extinguish();
    //     }
    //     if (this.inGround && !bl) {
    //         if (this.inBlockState != blockState && this.shouldFall()) {
    //             this.fall();
    //         } else if (!this.getWorld().isClient) {
    //             this.age();
    //         }
    //         ++this.inGroundTime;
    //         return;
    //     }
    //     this.inGroundTime = 0;
    //     Vec3d vec3d3 = this.getPos();
    //     vec3d2 = vec3d3.add(vec3d);
    //     HitResult hitResult = this.getWorld().raycast(new RaycastContext(vec3d3, vec3d2, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
    //     if (hitResult.getType() != HitResult.Type.MISS) {
    //         vec3d2 = hitResult.getPos();
    //     }
    //     // while (!this.isRemoved()) {
    //     //     EntityHitResult entityHitResult = this.getEntityCollision(vec3d3, vec3d2);
    //     //     if (entityHitResult != null) {
    //     //         hitResult = entityHitResult;
    //     //     }
    //     //     if (hitResult != null && hitResult.getType() == HitResult.Type.ENTITY) {
    //     //         Entity entity = ((EntityHitResult)hitResult).getEntity();
    //     //         Entity entity2 = this.getOwner();
    //     //         if (entity instanceof PlayerEntity && entity2 instanceof PlayerEntity && !((PlayerEntity)entity2).shouldDamagePlayer((PlayerEntity)entity)) {
    //     //             hitResult = null;
    //     //             entityHitResult = null;
    //     //         }
    //     //     }
    //     //     if (hitResult != null && !bl) {
    //     //         this.onCollision(hitResult);
    //     //         this.velocityDirty = true;
    //     //     }
    //     //     if (entityHitResult == null || this.getPierceLevel() <= 0) break;
    //     //     hitResult = null;
    //     // }
    //     vec3d = this.getVelocity();
    //     double e = vec3d.x;
    //     double f = vec3d.y;
    //     double g = vec3d.z;
    //     if (this.isCritical()) {
    //         for (int i = 0; i < 4; ++i) {
    //             this.getWorld().addParticle(ParticleTypes.CRIT, this.getX() + e * (double)i / 4.0, this.getY() + f * (double)i / 4.0, this.getZ() + g * (double)i / 4.0, -e, -f + 0.2, -g);
    //         }
    //     }
    //     double h = this.getX() + e;
    //     double j = this.getY() + f;
    //     double k = this.getZ() + g;
    //     double l = vec3d.horizontalLength();
    //     if (bl) {
    //         this.setYaw((float)(MathHelper.atan2(-e, -g) * 57.2957763671875));
    //     } else {
    //         this.setYaw((float)(MathHelper.atan2(e, g) * 57.2957763671875));
    //     }
    //     this.setPitch((float)(MathHelper.atan2(f, l) * 57.2957763671875));
    //     this.setPitch(PersistentProjectileEntity.updateRotation(this.prevPitch, this.getPitch()));
    //     this.setYaw(PersistentProjectileEntity.updateRotation(this.prevYaw, this.getYaw()));
    //     float m = 0.99f;
    //     float n = 0.05f;
    //     if (this.isTouchingWater()) {
    //         for (int o = 0; o < 4; ++o) {
    //             float p = 0.25f;
    //             this.getWorld().addParticle(ParticleTypes.BUBBLE, h - e * 0.25, j - f * 0.25, k - g * 0.25, e, f, g);
    //         }
    //         m = this.getDragInWater();
    //     }
    //     this.setVelocity(vec3d.multiply(m));
    //     if (!this.hasNoGravity() && !bl) {
    //         Vec3d vec3d4 = this.getVelocity();
    //         this.setVelocity(vec3d4.x, vec3d4.y - (double)0.05f, vec3d4.z);
    //     }
    //     this.setPosition(h, j, k);
    //     this.checkBlockCollision();
    // }

    // @Override
    // protected void onEntityHit(HitResult hitResult){
    //     if(this.getWorld().isClient()){
    //         return;
    //     }
    //     if(hitResult.getType() == HitResult.Type.E && hitResult instanceof BlockHitResult blockHitResult) {
    //         BlockEntity blockEntity = this.getWorld().getBlockEntity(blockHitResult.getBlockPos());
    //         if(blockEntity instanceof BasketEntity basketEntity){
    //             basketEntity.onEntityCollided(this.getWorld(), basketEntity.getPos(), this);
    //         }
    //     }
    // }

    
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    public void remove(RemovalReason reason) {
        McDiscGolf.LOGGER.info("Remove frisbee");
        super.remove(reason);
    }
    // public void onPlayerCollision(PlayerEntity player) {
    //     if (this.isOwner(player) || this.getOwner() == null) {
    //         super.onPlayerCollision(player);
    //     }
    // }

    // public void readCustomDataFromNbt(NbtCompound nbt) {
    //     super.readCustomDataFromNbt(nbt);
    //     if (nbt.contains("Frisbee", 10)) {
    //        this.frisbeeStack = ItemStack.fromNbt(nbt.getCompound("Frisbee"));
    //     }
    // }

    // public void writeCustomDataToNbt(NbtCompound nbt) {
    //     super.writeCustomDataToNbt(nbt);
    //     nbt.put("Frisbee", this.frisbeeStack.writeNbt(new NbtCompound()));
    // }

    // protected float getDragInWater() {
    //     return 0.99F;
    // }
    
    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }
}
