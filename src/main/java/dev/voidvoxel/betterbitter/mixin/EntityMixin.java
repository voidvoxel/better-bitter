package dev.voidvoxel.betterbitter.mixin;

import dev.voidvoxel.betterbitter.api.EntityHelper;
import dev.voidvoxel.betterbitter.api.MovingEntity;
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

/**
 * A mixin for the `Entity` class.
 */
@Mixin(Entity.class)
public abstract class EntityMixin implements MovingEntity {
    @Unique
    private boolean betterBitter$property$isMovingHorizontally;

    @Unique
    private boolean betterBitter$property$isMovingVertically;

    @Unique
    private Vec3d betterBitter$property$previousPosition;

    @Shadow
    private World world;

    @Shadow
    public abstract double getFluidHeight(TagKey<Fluid> fluid);

    @Shadow
    private boolean onGround;

    @Final
    @Shadow
    @Nullable
    private Set<TagKey<Fluid>> submergedFluidTag;

    @Shadow
    protected Object2DoubleMap<TagKey<Fluid>> fluidHeight;

    @Shadow public abstract Vec3d getPos();

    @Shadow
    public abstract double getX();

    @Shadow public abstract double getY();

    @Shadow public abstract double getZ();

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    private void betterBitter$applyFireResistance(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object)this;

        if(entity instanceof LivingEntity livingEntity) {
            if (EntityHelper.isDematerialized(livingEntity)) {
                if (betterBitter$isMoving()) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;ofFloored(DDD)Lnet/minecraft/util/math/BlockPos;"), method = "pushOutOfBlocks", cancellable = true)
    protected void pushOutOfBlocks(double x, double y, double z, CallbackInfo info) {
        Entity entity = (Entity)(Object)this;

        if (entity instanceof LivingEntity livingEntity) {
            if (EntityHelper.isDematerialized(livingEntity))
                info.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void betterBitter$resetMovementProperties(CallbackInfo callbackInfo) {
        betterBitter$property$isMovingHorizontally = betterBitter$property$isMovingVertically = false;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void betterBitter$applyFear(CallbackInfo callbackInfo) {
        Entity entity = (Entity)(Object)this;

        if (entity instanceof PlayerEntity livingEntity) {
            BlockPos blockPos = entity.getBlockPos();

            int preyLightLevel = world.getLightLevel(blockPos);
            int preyPositionY = blockPos.getY();

            if (
                    (preyPositionY < 0 || (
                            preyPositionY < 48 && preyLightLevel == 0
                    )) && !(livingEntity.hasStatusEffect(StatusEffects.NIGHT_VISION))
            ) {
                if (Math.random() <= 0.001) {
                    EntityHelper.scare(livingEntity);
                }
            }
        }
    }

    @Inject(method = "baseTick", at = @At("TAIL"))
    private void betterBitter$setMovementProperties(CallbackInfo callbackInfo) {
        if (betterBitter$property$previousPosition == null) {
            betterBitter$property$previousPosition = getPos();

            return;
        }

        double dx = betterBitter$property$previousPosition.x - getX();
        double dy = betterBitter$property$previousPosition.y - getY();
        double dz = betterBitter$property$previousPosition.z - getZ();

        betterBitter$property$previousPosition = getPos();

        if (Math.sqrt(dx * dx + dz * dz) >= 0.01) {
            betterBitter$property$isMovingHorizontally = true;
        }

        if (Math.sqrt(dy * dy) >= 0.01) {
            betterBitter$property$isMovingVertically = true;
        }
    }

    @Redirect(method = "method_30022", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getCollisionShape(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/shape/VoxelShape;"))
    private VoxelShape preventPhasingSuffocation(BlockState state, BlockView world, BlockPos pos) {
        Entity entity = (Entity)(Object)this;

        if (entity instanceof LivingEntity livingEntity) {
            if (Math.random() <= 0.001) {
                EntityHelper.applyHauntStatusEffects(livingEntity);
            }
        }

        return state.getCollisionShape(world, pos, ShapeContext.of(entity));
    }

    @Override
    public boolean betterBitter$isMoving() {
        return betterBitter$property$isMovingHorizontally
                || betterBitter$property$isMovingVertically;
    }

    @Override
    public boolean betterBitter$isMovingHorizontally() {
        return betterBitter$property$isMovingHorizontally;
    }

    @Override
    public boolean betterBitter$isMovingVertically() {
        return betterBitter$property$isMovingVertically;
    }
}
