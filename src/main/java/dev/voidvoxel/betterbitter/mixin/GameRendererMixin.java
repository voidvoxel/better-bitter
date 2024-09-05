package dev.voidvoxel.betterbitter.mixin;

import dev.voidvoxel.betterbitter.api.EntityHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Final
    @Shadow
    private Camera camera;

    @Final
    @Shadow
    MinecraftClient client;

    @Unique
    private final HashMap<BlockPos, BlockState> savedStates = new HashMap<>();

    @Inject(at = @At(value = "HEAD"), method = "render")
    private void beforeRender(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        Entity cameraFocusedEntity = camera.getFocusedEntity();

        assert client.world != null;

        // TODO: Invert this `!` if necessary.
        if (!EntityHelper.hasPhysicalForm(cameraFocusedEntity)) {
            Set<BlockPos> eyePositions = getEyePos();
            Set<BlockPos> noLongerEyePositions = new HashSet<>();

            for (BlockPos p : savedStates.keySet()) {
                if (!eyePositions.contains(p)) {
                    noLongerEyePositions.add(p);
                }
            }

            for (BlockPos eyePosition : noLongerEyePositions) {
                BlockState state = savedStates.get(eyePosition);
                client.world.setBlockState(eyePosition, state);
                savedStates.remove(eyePosition);
            }

            for (BlockPos p : eyePositions) {
                BlockState stateAtP = client.world.getBlockState(p);

                if (!savedStates.containsKey(p) && !client.world.isAir(p) && !(stateAtP.getBlock() instanceof FluidBlock)) {
                    savedStates.put(p, stateAtP);
                    client.world.setBlockState(p, Blocks.AIR.getDefaultState());
                }
            }
        } else if (!savedStates.isEmpty()) {
            Set<BlockPos> noLongerEyePositions = new HashSet<>(savedStates.keySet());

            for (BlockPos eyePosition : noLongerEyePositions) {
                BlockState state = savedStates.get(eyePosition);
                client.world.setBlockState(eyePosition, state);
                savedStates.remove(eyePosition);
            }
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;update(Lnet/minecraft/world/BlockView;Lnet/minecraft/entity/Entity;ZZF)V"), method = "renderWorld")
    private void preventThirdPerson(Camera camera, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {
        // TODO: Invert this `!` if necessary.
        if (!EntityHelper.hasPhysicalForm(camera.getFocusedEntity())) {
            camera.update(area, focusedEntity, false, false, tickDelta);
        } else {
            camera.update(area, focusedEntity, thirdPerson, inverseView, tickDelta);
        }
    }

    @Unique
    private Set<BlockPos> getEyePos() {
        return this.getEyePos(0.25f, 0.05f, 0.25f);
    }

    @Unique
    private Set<BlockPos> getEyePos(float rangeX, float rangeY, float rangeZ) {
        Vec3d pos = camera.getFocusedEntity().getPos().add(0, camera.getFocusedEntity().getEyeHeight(camera.getFocusedEntity().getPose()), 0);
        Box cameraBox = new Box(pos, pos);
        cameraBox = cameraBox.expand(rangeX, rangeY, rangeZ);
        HashSet<BlockPos> set = new HashSet<>();
        BlockPos.stream(cameraBox).forEach(p -> set.add(p.toImmutable()));
        return set;
    }
}
