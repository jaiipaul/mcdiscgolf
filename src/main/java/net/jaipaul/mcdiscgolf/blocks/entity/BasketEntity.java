package net.jaipaul.mcdiscgolf.blocks.entity;


import org.jetbrains.annotations.Nullable;


import net.jaipaul.mcdiscgolf.entity.FrisbeeEntity;
import net.jaipaul.mcdiscgolf.screen.BasketScreenHandler;
import net.jaipaul.mcdiscgolf.sounds.ModSounds;
import net.jaipaul.mcdiscgolf.utils.ImplementedInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;


public class BasketEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory{
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private static final VoxelShape CHAINS_SHAPE;

 
    public BasketEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASKET_ENTITY, pos, state);
    }
 
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BasketScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
      return createNbt();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }
 
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);

    }
    
    public void insertFrisbee(World world, BlockPos pos, FrisbeeEntity entity) {
        ItemStack stack = entity.getStack();
        if (!stack.isEmpty()) {
            for (int i = 0; i < this.inventory.size(); ++i) {
                if (this.getStack(i).isEmpty()) {
                    this.setStack(i, stack);
                    this.markDirty();
                    entity.discard();
                    return;
                };
            }
        }
            
    }
    public void onEntityCollided(World world, BlockPos pos, Entity entity, HitResult hit) {
        if (entity instanceof FrisbeeEntity && this.getChainsBox().contains(hit.getPos())){
            this.insertFrisbee(world, pos, (FrisbeeEntity)entity);
            world.playSound((PlayerEntity)null, pos, ModSounds.BASKET_CHAINS, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    public Box getChainsBox() {
        return CHAINS_SHAPE.getBoundingBox().offset(this.pos);
    }
    static {
        CHAINS_SHAPE = Block.createCuboidShape(0.5, 18, 0.5, 15.5, 30.0, 15.5);
    }
}
