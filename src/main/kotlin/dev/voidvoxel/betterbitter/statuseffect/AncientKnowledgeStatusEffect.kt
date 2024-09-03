package dev.voidvoxel.betterbitter.statuseffect

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box


class AncientKnowledgeStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0x6c6cb7) {
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
        val ANCIENT_KNOWLEDGE_STATUS_EFFECT: StatusEffect = AncientKnowledgeStatusEffect()

        @JvmStatic
        var ANCIENT_KNOWLEDGE_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.STATUS_EFFECT, Identifier.of("betterbitter", "ancient_knowledge"), ANCIENT_KNOWLEDGE_STATUS_EFFECT)
            ANCIENT_KNOWLEDGE_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(ANCIENT_KNOWLEDGE_STATUS_EFFECT)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return false

        if (entity is PlayerEntity) {
            if (Math.random() < 0.01 * (1 + amplifier))
                entity.addExperience(1 + amplifier)
        }

        // Apply absorption.
        entity.removeStatusEffect(StatusEffects.ABSORPTION)
        entity.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.ABSORPTION,
                20,
                amplifier
            )
        )

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

        for (nearbyEntity in nearbyEntities) {
            if (nearbyEntity == entity || nearbyEntity.equals(entity))
                continue

            nearbyEntity.removeStatusEffect(StatusEffects.GLOWING)
            nearbyEntity.addStatusEffect(StatusEffectInstance(StatusEffects.GLOWING, 20 * (1 + amplifier)), entity)

            nearbyEntity.removeStatusEffect(StatusEffects.WIND_CHARGED)
            nearbyEntity.addStatusEffect(StatusEffectInstance(StatusEffects.WIND_CHARGED, 20 * (1 + amplifier), amplifier), entity)
        }

        return super.applyUpdateEffect(entity, amplifier)
    }
}