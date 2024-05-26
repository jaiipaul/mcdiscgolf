package net.jaipaul.mcdiscgolf.blocks.entity;


import org.jetbrains.annotations.Nullable;

import java.util.List;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.entity.FrisbeeEntity;
import net.jaipaul.mcdiscgolf.entity.ModEntities;
import net.jaipaul.mcdiscgolf.screen.BasketScreenHandler;
import net.jaipaul.mcdiscgolf.utils.ImplementedInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;


public class BasketEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
    private static final VoxelShape CHAINS_SHAPE;

 
    public BasketEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASKET_ENTITY, pos, state);
    }
 
 
    //From the ImplementedInventory Interface
 
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
 
    //These Methods are from the NamedScreenHandlerFactory Interface
    //createMenu creates the ScreenHandler itself
    //getDisplayName will Provide its name which is normally shown at the top
    
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new BasketScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        // for 1.19+
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
        // for earlier versions
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
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

    @Override
    public int[] getAvailableSlots(Direction side) {
        // Just return an array of all slots
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
 
        return result;
    }
 
    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return (direction != Direction.UP && direction != Direction.DOWN);
    }
 
    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return true;
    }

    public static void tick(World world, BlockPos pos, BlockState state, BasketEntity be) {
        List<FrisbeeEntity> frisbees = world.getEntitiesByType(ModEntities.FRISBEE_ENTITY_TYPE, be.getChainsBox(), EntityPredicates.VALID_ENTITY);
        for (int i = 0; i < frisbees.size(); ++i) {
            FrisbeeEntity frisbeeEntity = (FrisbeeEntity)frisbees.get(i);
            be.insertFrisbee(world, pos, frisbeeEntity);
        }
    }

    
    public void insertFrisbee(World world, BlockPos pos, FrisbeeEntity entity) {
        ItemStack stack = entity.getStack();
        if (!stack.isEmpty()) {
            // McDiscGolf.LOGGER.info(String.format("%d", this.size()));
            for (int i = 0; i < this.inventory.size(); ++i) {
                if (this.getStack(i).isEmpty()) {
                    this.setStack(i, stack);
                    this.markDirty();
                    entity.discard();
                    McDiscGolf.LOGGER.info(String.format("Added stack at %d", i));
                    return;
                };
            }
        }
            
    }
    public void onEntityCollided(World world, BlockPos pos, Entity entity) {
        if (entity instanceof FrisbeeEntity && this.entityInChains(entity)){
            McDiscGolf.LOGGER.info("Basket Hit");
            this.insertFrisbee(world, pos, (FrisbeeEntity)entity);
        }    
    }

    public Boolean entityInChains(Entity entity) {
        Box chainbox = this.getChainsBox();
        McDiscGolf.LOGGER.info("Chains bounding box: ");
        McDiscGolf.LOGGER.info(chainbox.toString());
        Box entitybox = entity.getBoundingBox();
        McDiscGolf.LOGGER.info("Frisbee bounding box: ");
        McDiscGolf.LOGGER.info(entitybox.toString());

        return VoxelShapes.matchesAnywhere(VoxelShapes.cuboid(entitybox), VoxelShapes.cuboid(chainbox), BooleanBiFunction.AND);
    }

    public Box getChainsBox() {
        return CHAINS_SHAPE.getBoundingBox().offset(this.pos);
    }
    static {
        CHAINS_SHAPE = Block.createCuboidShape(0.5, 18, 0.5, 15.5, 30.0, 15.5);
    }
}
