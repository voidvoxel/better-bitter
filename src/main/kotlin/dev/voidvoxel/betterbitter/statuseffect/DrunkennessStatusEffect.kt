package dev.voidvoxel.betterbitter.statuseffect

import dev.voidvoxel.betterbitter.api.EntityHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier


class DrunkennessStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0x30432b) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = 3600
        @JvmStatic
        val EXTENDED_DURATION: Int = 9600

        @JvmStatic
        val MAX_AMPLIFIER: Int = 1

        @JvmStatic
        val DRUNKENNESS_STATUS_EFFECT: StatusEffect = DrunkennessStatusEffect()

        @JvmStatic
        var DRUNKENNESS_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.STATUS_EFFECT, Identifier.of("betterbitter", "drunkenness"), DRUNKENNESS_STATUS_EFFECT)
            DRUNKENNESS_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(DRUNKENNESS_STATUS_EFFECT)
        }
    }

    override fun onApplied(entity: LivingEntity?, amplifier: Int) {
        super.onApplied(entity, amplifier)

        if (entity == null)
            return

        if (amplifier > MAX_AMPLIFIER) {
            EntityHelper.scare(entity, amplifier - MAX_AMPLIFIER)
        }
    }

    override fun onEntityRemoval(entity: LivingEntity, amplifier: Int, reason: Entity.RemovalReason?) {
        super.onEntityRemoval(entity, amplifier, reason)
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val amplifierCoerced = amplifier.coerceAtMost(MAX_AMPLIFIER)

        return duration % (40 / (1 + amplifierCoerced)) == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return true

        entity.frozenTicks += 45 / (1 + amplifier) + (amplifier * 10)

        if (Math.random() <= 0.2) {
            EntityHelper.playBurpSound(entity)

            if (Math.random() <= 0.4) {
                entity.addStatusEffect(
                    StatusEffectInstance(
                        StatusEffects.NAUSEA,
                        100 * amplifier
                    )
                )
            } else if (amplifier > 0 && Math.random() <= 0.05) {
                EntityHelper.scare(entity, 0)
            }
        }

        return super.applyUpdateEffect(entity, amplifier)
    }
}