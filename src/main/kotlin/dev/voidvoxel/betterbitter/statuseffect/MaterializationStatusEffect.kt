package dev.voidvoxel.betterbitter.statuseffect

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier


class MaterializationStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0xdfba2b) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = 3600
        @JvmStatic
        val EXTENDED_DURATION: Int = 9600

        @JvmStatic
        val MATERIALIZATION_STATUS_EFFECT: StatusEffect = MaterializationStatusEffect()

        @JvmStatic
        var MATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of(
                    "betterbitter",
                    "materialization"
                ),
                MATERIALIZATION_STATUS_EFFECT
            )

            MATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(MATERIALIZATION_STATUS_EFFECT)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity != null && !entity.hasStatusEffect(BetterBitterStatusEffects.DEMATERIALIZATION))
            entity.removeStatusEffect(BetterBitterStatusEffects.DEMATERIALIZATION)

        return super.applyUpdateEffect(entity, amplifier)
    }
}