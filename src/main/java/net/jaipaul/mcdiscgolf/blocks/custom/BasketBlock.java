package net.jaipaul.mcdiscgolf.blocks.custom;


import org.jetbrains.annotations.Nullable;

import net.jaipaul.mcdiscgolf.McDiscGolf;
import net.jaipaul.mcdiscgolf.blocks.entity.BasketEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;

public class BasketBlock extends BlockWithEntity{
    protected static final VoxelShape BASKET_SHAPE = Block.createCuboidShape(0.0, 12.0, 0.0, 16.0, 18.0, 16.0);
    protected static final VoxelShape POLE_SHAPE = Block.createCuboidShape(7.0, 0.0, 7.0, 9.0, 18.0, 9.0);
    protected static final VoxelShape CHAINS_SHAPE = Block.createCuboidShape(4.0, 2.0, 4.0, 12.0, 14.0, 12.0);
    protected static final VoxelShape RIM_SHAPE = Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
    private static VoxelShape TOP_SHAPE = VoxelShapes.union(CHAINS_SHAPE, RIM_SHAPE);
    private static VoxelShape BOTTOM_SHAPE = VoxelShapes.union(BASKET_SHAPE, POLE_SHAPE);
    
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    public BasketBlock(Settings settings){
        super(settings);
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(HALF, DoubleBlockHalf.LOWER));
    }
    
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if(state.get(HALF) == DoubleBlockHalf.UPPER){
            McDiscGolf.LOGGER.info("Create basket entity");
            return new BasketEntity(pos.down(), state);
        }
        return null;
    }
 
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            return TOP_SHAPE;
        }
        return BOTTOM_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            BlockPos basket_pos = pos;
            if(state.get(HALF) == DoubleBlockHalf.UPPER){
                basket_pos = pos.down();
            }
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, basket_pos);
 
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (!(direction.getAxis() != Direction.Axis.Y || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf)) {
            return Blocks.AIR.getDefaultState();
        }
        // if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
        //     return Blocks.AIR.getDefaultState();
        // }
        return state;
    }


    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            BlockPos blockPos;
            BlockState blockState;
            DoubleBlockHalf doubleBlockHalf = state.get(HALF);
            if (doubleBlockHalf == DoubleBlockHalf.UPPER && (blockState = world.getBlockState(blockPos = pos.down())).isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), (BlockState)state.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
        BlockEntity blockEntity;
        if (itemStack.hasCustomName() && (blockEntity = world.getBlockEntity(pos.up())) instanceof HopperBlockEntity) {
            ((HopperBlockEntity)blockEntity).setCustomName(itemStack.getName());
        }
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (state.get(HALF) == DoubleBlockHalf.LOWER) {
            return blockState.isSideSolidFullSquare(world, blockPos, Direction.UP);
        }
        return blockState.isOf(this);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.hasBlockEntity() && state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BasketEntity) {
                ItemScatterer.spawn(world, pos, (BasketEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            world.removeBlockEntity(pos);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }
 
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }
    

    @Override
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if(state.get(HALF) == DoubleBlockHalf.UPPER){
            BlockEntity blockEntity = world.getBlockEntity(hit.getBlockPos().down());
            if (blockEntity instanceof BasketEntity basketEntity) {
                basketEntity.onEntityCollided(world, basketEntity.getPos(), projectile, hit);
            }
        }
    }
}
