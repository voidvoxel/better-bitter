package dev.voidvoxel.betterbitter.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.serialization.MapCodec;
import dev.voidvoxel.betterbitter.api.EntityHelper;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlockStateMixin extends State<Block, BlockState> {
    @Shadow
    public abstract Block getBlock();

    @Shadow
    protected abstract BlockState asBlockState();

    protected AbstractBlockStateMixin(Block owner, Reference2ObjectArrayMap<Property<?>, Comparable<?>> entries, MapCodec<BlockState> codec) {
        super(owner, entries, codec);
    }

    @ModifyReturnValue(method = "getOutlineShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;", at = @At("RETURN"))
    private VoxelShape betterBitter$preventBlockSelection(VoxelShape original, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {

        Entity entity;
        if (!(shapeContext instanceof EntityShapeContext esc) || (entity = esc.getEntity()) == null) {
            return original;
        }

        return EntityHelper.isDematerialized(entity) ? VoxelShapes.empty() : original;

    }

    @ModifyReturnValue(method = "getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/ShapeContext;)Lnet/minecraft/util/shape/VoxelShape;", at = @At("RETURN"))
    private VoxelShape betterBitter$phaseThroughBlocks(VoxelShape original, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        Entity entity;
        if (original.isEmpty() || !(shapeContext instanceof EntityShapeContext esc) || (entity = esc.getEntity()) == null) {
            return original;
        }

        if (entity instanceof LivingEntity livingEntity) {
            final Block block = livingEntity.getWorld().getBlockState(blockPos).getBlock();
            final boolean doesDematerializationApply = !(block.getName().toString().toLowerCase().contains("obsidian")) && (!betterBitter$isAbove(entity, original, blockPos) || EntityHelper.shouldDematerializeDownward(livingEntity));

            return (!EntityHelper.hasPhysicalForm(livingEntity) && doesDematerializationApply) ? VoxelShapes.empty() : original;
        }

        return original;
    }

    @Unique
    private boolean betterBitter$isAbove(Entity entity, VoxelShape shape, BlockPos pos) {
        return entity.getY() > (double) pos.getY() + shape.getMax(Direction.Axis.Y) - (entity.isOnGround() ? 8.05 / 16.0 : 0.0015);
    }

    @WrapWithCondition(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;onEntityCollision(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)V"))
    private boolean betterBitter$preventOnEntityCollisionCallWhenPhasing(Block instance, BlockState state, World world, BlockPos blockPos, Entity entity) {
        return !EntityHelper.isDematerialized(entity);
    }
}
