package dev.voidvoxel.betterbitter.statuseffect

import dev.voidvoxel.betterbitter.api.EntityHelper
import dev.voidvoxel.betterbitter.potion.FearPotion
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.util.math.Box


class UnnervingStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0x6c6cb7) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = 900
        @JvmStatic
        val BASE_RADIUS: Int = 16
        @JvmStatic
        val EXTENDED_DURATION: Int = BASE_DURATION * 2
        @JvmStatic
        val AMPLIFIED_DURATION: Int = BASE_DURATION / 2

        @JvmStatic
        val UNNERVING_STATUS_EFFECT: StatusEffect = UnnervingStatusEffect()

        @JvmStatic
        var UNNERVING_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.STATUS_EFFECT, Identifier.of("betterbitter", "unnerving"), UNNERVING_STATUS_EFFECT)
            UNNERVING_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(UNNERVING_STATUS_EFFECT)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % (40 / amplifier) == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return false

        val eyePosition = entity.eyePos
        val world = entity.world

        val radius = BASE_RADIUS + BASE_RADIUS * amplifier

        val x1 = eyePosition.x - radius
        val y1 = eyePosition.y - radius
        val z1 = eyePosition.z - radius
        val x2 = eyePosition.x + radius
        val y2 = eyePosition.y + radius
        val z2 = eyePosition.z + radius

        val nearbyEntities = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(x1, y1, z1, x2, y2, z2),
            EntityPredicates.VALID_LIVING_ENTITY
        )

        EntityHelper.playAmbientHuntingSound(entity)

        for (nearbyEntity in nearbyEntities) {
            if (nearbyEntity == entity || nearbyEntity.equals(entity))
                continue

            if (nearbyEntity is PassiveEntity)
                continue

            if (nearbyEntity !is HostileEntity) {
                val isTargetingEntity = nearbyEntity.isTarget(entity, TargetPredicate.DEFAULT)

                if (!isTargetingEntity)
                    continue
            }

            if (!nearbyEntity.hasStatusEffect(BetterBitterStatusEffects.FEAR)) {
                EntityHelper.scare(nearbyEntity, amplifier)
            }
        }

        return super.applyUpdateEffect(entity, amplifier)
    }
}