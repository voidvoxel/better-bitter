package dev.voidvoxel.betterbitter.statuseffect

import dev.voidvoxel.betterbitter.api.ClientHelper
import dev.voidvoxel.betterbitter.api.EntityHelper
import dev.voidvoxel.betterbitter.entity.mob.GildedPhantomEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.math.Box


class HuntMarkStatusEffect : StatusEffect(StatusEffectCategory.NEUTRAL, 0xfbf25c) {
    companion object {
        @JvmStatic
        val BASE_DURATION: Int = (20 * 60 * 2) + (20 * 58) * 2 // 2:58 * 2

        @JvmStatic
        val MAX_AMPLIFIER: Int = 2

        @JvmStatic
        val HUNT_MARK_STATUS_EFFECT: StatusEffect = HuntMarkStatusEffect()

        @JvmStatic
        var HUNT_MARK_STATUS_EFFECT_REGISTRY_ENTRY: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.STATUS_EFFECT, Identifier.of("betterbitter", "mark_of_the_hunt"), HUNT_MARK_STATUS_EFFECT)
            HUNT_MARK_STATUS_EFFECT_REGISTRY_ENTRY = Registries.STATUS_EFFECT.getEntry(HUNT_MARK_STATUS_EFFECT)
        }
    }

    override fun onApplied(entity: LivingEntity?, amplifier: Int) {
        super.onApplied(entity, amplifier)

        if (entity == null)
            return

        if (entity is PlayerEntity)
            ClientHelper.stopMusic(entity)

        entity.playSound(
            SoundEvent.of(
                Identifier.of(
                    "minecraft",
                    "music_disc.5"
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

        if (amplifier > 0) {
            entity.addStatusEffect(
                StatusEffectInstance(
                    BetterBitterStatusEffects.HUNT_MARK,
                    BASE_DURATION,
                    0,
                    true,
                    false,
                    false
                )
            )
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val amplifierCoerced = amplifier.coerceAtMost(MAX_AMPLIFIER)

        return duration % ((20 * 8) / (1 + amplifierCoerced)) == 0
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity == null)
            return false

        if (!entity.hasStatusEffect(BetterBitterStatusEffects.FEAR)) {
            EntityHelper.scare(entity, amplifier)

            entity.addStatusEffect(
                StatusEffectInstance(
                    StatusEffects.DARKNESS,
                    FearStatusEffect.BASE_DURATION,
                    0,
                    true,
                    false,
                    false
                )
            )
        }

        EntityHelper.playAmbientHuntingSound(entity)

        if (Math.random() <= 0.1) {
            EntityHelper.playHuntingSound(entity)
        }

        if (Math.random() <= 0.2) {
            EntityHelper.playHauntSound(entity)

            if (Math.random() <= 0.2) {
                EntityHelper.applyHauntStatusEffects(entity, amplifier)
            }
        }

        val eyePosition = entity.eyePos
        val world = entity.world

        val radius = UnnervingStatusEffect.BASE_RADIUS + UnnervingStatusEffect.BASE_RADIUS * amplifier

        val x1 = eyePosition.x - radius
        val y1 = eyePosition.y - radius
        val z1 = eyePosition.z - radius
        val x2 = eyePosition.x + radius
        val y2 = eyePosition.y + radius
        val z2 = eyePosition.z + radius

        val nearbyEntities = world.getEntitiesByClass(
            GildedPhantomEntity::class.java,
            Box(x1, y1, z1, x2, y2, z2),
            EntityPredicates.VALID_LIVING_ENTITY
        )

        var gildedPhantomCount: Int = 0

        for (nearbyEntity in nearbyEntities) {
            gildedPhantomCount += 1
        }

        if (gildedPhantomCount == 0) {
            val gildedPhantom = GildedPhantomEntity(
                GildedPhantomEntity.ENTITY_TYPE,
                world
            )

            var x = eyePosition.x + radius * Math.random()
            var y = eyePosition.y + radius * Math.random()
            var z = eyePosition.z + radius * Math.random()

            if (Math.random() <= 0.5)
                x = -x

            if (Math.random() <= 0.5)
                y = -y

            if (Math.random() <= 0.5)
                z = -z

            gildedPhantom.updatePosition(x, y, z)
        }

        return super.applyUpdateEffect(entity, amplifier)
    }
}