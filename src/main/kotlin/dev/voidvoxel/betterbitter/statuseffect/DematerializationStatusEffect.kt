package dev.voidvoxel.betterbitter.statuseffect

import net.minecraft.block.AirBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos


class DematerializationStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0x5e5368) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = 900
        @JvmStatic
        val EXTENDED_DURATION: Int = BASE_DURATION * 2

        @JvmStatic
        val DEMATERIALIZATION_STATUS_EFFECT: StatusEffect = DematerializationStatusEffect()

        @JvmStatic
        var DEMATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of(
                    "betterbitter",
                    "dematerialization"
                ),
                DEMATERIALIZATION_STATUS_EFFECT
            )

            DEMATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(DEMATERIALIZATION_STATUS_EFFECT)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return false

        entity.isInvisible = true

        val eyePosition = entity.eyePos
        val eyeBlockPosition = BlockPos(Math.round(eyePosition.x).toInt(), Math.round(eyePosition.y).toInt(), Math.round(eyePosition.z).toInt())
        val world = entity.world
        val blockAboveEntity = world.getBlockState(eyeBlockPosition).block

        val isVisionObstructed = blockAboveEntity !is AirBlock

        if (isVisionObstructed && entity is PlayerEntity) {
            // TODO: Make the player able to see through blocks.
        }

        return super.applyUpdateEffect(entity, amplifier)
    }

    override fun onEntityRemoval(entity: LivingEntity?, amplifier: Int, reason: Entity.RemovalReason?) {
        if (entity == null)
            return

        entity.isInvisible = false

//        if (!entity.hasStatusEffect(StatusEffects.INVISIBILITY))
//            entity.isInvisible = false

        super.onEntityRemoval(entity, amplifier, reason)
    }
}