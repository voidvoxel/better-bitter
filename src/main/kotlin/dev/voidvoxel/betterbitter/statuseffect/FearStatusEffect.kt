package dev.voidvoxel.betterbitter.statuseffect

import dev.voidvoxel.betterbitter.api.EntityHelper
import dev.voidvoxel.betterbitter.entity.mob.GildedPhantomEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier


class FearStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0x30432b) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = 600
        @JvmStatic
        val EXTENDED_DURATION: Int = BASE_DURATION * 2

        @JvmStatic
        val MAX_AMPLIFIER: Int = 2

        @JvmStatic
        val FEAR_STATUS_EFFECT: StatusEffect = FearStatusEffect()

        @JvmStatic
        var FEAR_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.STATUS_EFFECT, Identifier.of("betterbitter", "fear"), FEAR_STATUS_EFFECT)
            FEAR_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(FEAR_STATUS_EFFECT)
        }
    }

    override fun onApplied(entity: LivingEntity?, amplifier: Int) {
        if (entity == null)
            return

        super.onApplied(entity, amplifier)

        entity.playSound(
            SoundEvent.of(
                Identifier.of(
                    "minecraft",
                    "entity.warden.emerge"
                )
            ),
            GildedPhantomEntity.HUNT_RADIUS.toFloat(),
            0.5f,
        )
    }

    override fun onEntityRemoval(entity: LivingEntity?, amplifier: Int, reason: Entity.RemovalReason?) {
        if (entity == null)
            return

        super.onEntityRemoval(entity, amplifier, reason)

        if (amplifier >= MAX_AMPLIFIER) {
            entity.removeStatusEffect(BetterBitterStatusEffects.FEAR)
            entity.addStatusEffect(
                StatusEffectInstance(
                    BetterBitterStatusEffects.HUNT_MARK,
                    BASE_DURATION,
                    amplifier - MAX_AMPLIFIER,
                    true,
                    false,
                    false
                )
            )
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val amplifierCoerced = amplifier.coerceAtMost(MAX_AMPLIFIER)

        return duration % (40 / (1 + amplifierCoerced)) == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return false

        EntityHelper.playHeartbeatSound(entity)

        if (Math.random() <= 0.2) {
            EntityHelper.playHauntSound(entity)

            if (Math.random() <= 0.2)
                EntityHelper.applyHauntStatusEffects(entity, amplifier)
        }

        return super.applyUpdateEffect(entity, amplifier)
    }
}