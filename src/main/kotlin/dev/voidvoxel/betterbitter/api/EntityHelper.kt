package dev.voidvoxel.betterbitter.api

import dev.voidvoxel.betterbitter.potion.AlePotion
import dev.voidvoxel.betterbitter.potion.FearPotion
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import dev.voidvoxel.betterbitter.statuseffect.DematerializationStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.DrunkennessStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.FearStatusEffect
import net.minecraft.block.AirBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i
import net.minecraft.world.World

object EntityHelper {
    @JvmStatic
    fun applyHauntStatusEffects(entity: Entity) {
        applyHauntStatusEffects(entity, 0)
    }

    @JvmStatic
    fun applyHauntStatusEffects(entity: Entity, amplifier: Int) {
        if (entity !is LivingEntity)
            return

        scare(entity)

        // Apply Darkness.
        entity.removeStatusEffect(StatusEffects.DARKNESS)
        entity.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.DARKNESS,
                400,
                amplifier,
                true,
                false,
                false
            )
        )

        // Apply Nausea.
        entity.removeStatusEffect(StatusEffects.NAUSEA)
        entity.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.NAUSEA,
                120,
                amplifier,
                true,
                false,
                false
            )
        )

        // Apply Weakness.
        entity.removeStatusEffect(StatusEffects.WEAKNESS)
        entity.addStatusEffect(
            StatusEffectInstance(
                StatusEffects.WEAKNESS,
                100,
                amplifier,
                true,
                false,
                false
            )
        )
    }

    @JvmStatic
    fun hasAncientKnowledge(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            return entity.hasStatusEffect(BetterBitterStatusEffects.ANCIENT_KNOWLEDGE)
        }

        return false
    }

    @JvmStatic
    fun hasPhysicalForm(entity: Entity): Boolean {
        return !isDematerialized(entity)
    }

    @JvmStatic
    fun isDematerialized(entity: Entity): Boolean {
        if (entity is LivingEntity)
            return entity.hasStatusEffect(DematerializationStatusEffect.DEMATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY)

        return false
    }

    @JvmStatic
    fun isVisionCovered(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            return entity.world.getBlockState(BlockPos(Vec3i(Math.round(entity.eyePos.x).toInt(), Math.round(entity.eyePos.y).toInt(), Math.round(entity.eyePos.z).toInt()))).block !is AirBlock
        }

        return false
    }

    @JvmStatic
    fun playHauntSound(entity: LivingEntity) {
        entity.playSound(
            SoundEvent.of(
                Identifier.of("minecraft", "ambient.cave")
            ),
            1.0f,
            0.5f
        )
    }

    @JvmStatic
    fun playBurpSound(entity: LivingEntity) {
        entity.playSound(
            SoundEvent.of(
                Identifier.of("minecraft", "entity.player.burp")
            ),
            1.0f + 0.1f * Math.random().toFloat(),
            entity.soundPitch + 0.1f * Math.random().toFloat()
        )
    }

    @JvmStatic
    fun playHeartbeatSound(entity: LivingEntity) {
        entity.playSound(
            SoundEvent.of(
                Identifier.of("minecraft", "entity.warden.heartbeat")
            ),
            1.0f,
            0.5f
        )
    }

    @JvmStatic
    fun playAmbientHuntingSound(prey: LivingEntity) {
        val radius = 20

        val offsetX = Math.random() * radius * 2 - radius
        val offsetY = Math.random() * radius * 2 - radius
        val offsetZ = Math.random() * radius * 2 - radius

        val blockPos: BlockPos

        if (Math.random() <= 0.2) {
            blockPos = BlockPos(
                Vec3i(
                    (prey.x + offsetX).toInt(),
                    (prey.y + offsetY).toInt(),
                    (prey.z + offsetZ).toInt()
                )
            )
        } else
            blockPos = prey.blockPos

        playAmbientHuntingSound(prey, blockPos)
    }

    @JvmStatic
    fun playAmbientHuntingSound(prey: LivingEntity, blockPos: BlockPos) {
        val world = prey.world

        playAmbientHuntingSound(prey, blockPos, world)
    }

    @JvmStatic
    fun playAmbientHuntingSound(prey: LivingEntity, blockPos: BlockPos, world: World) {
        world.playSound(
            prey,
            blockPos,
            SoundEvent.of(
                Identifier.of("minecraft", "item.goat_horn.sound.6")
            ),
            SoundCategory.HOSTILE,
            1.0f,
            0.5f
        )

        world.playSound(
            prey,
            blockPos,
            SoundEvent.of(
                Identifier.of("minecraft", "item.axe.scrape")
            ),
            SoundCategory.HOSTILE,
            1.0f,
            0.5f
        )
    }

    @JvmStatic
    fun playHuntingSound(entity: Entity) {
        val radius = 20

        val offsetX = Math.random() * radius * 2 - radius
        val offsetY = Math.random() * radius * 2 - radius
        val offsetZ = Math.random() * radius * 2 - radius

        val blockPos = BlockPos(
            Vec3i(
                (entity.x + offsetX).toInt(),
                (entity.y + offsetY).toInt(),
                (entity.z + offsetZ).toInt()
            )
        )

        val world = entity.world

        world.playSound(
            entity,
            blockPos,
            SoundEvent.of(
                Identifier.of("minecraft", "entity.warden.listening_angry")
            ),
            SoundCategory.HOSTILE,
            1.0f,
            0.5f
        )

        world.playSound(
            entity,
            blockPos,
            SoundEvent.of(
                Identifier.of("minecraft", "entity.hoglin.converted_to_zombified")
            ),
            SoundCategory.HOSTILE,
            1.0f,
            0.5f
        )
    }

    @JvmStatic
    fun scare(entity: LivingEntity) {
        scare(entity, 0)
    }

    @JvmStatic
    fun scare(entity: LivingEntity, amplifier: Int) {
        if (Math.random() <= 0.1 + 0.1 * amplifier)
            entity.frozenTicks += 50 + 50 * amplifier

        // Apply Fear.
        entity.removeStatusEffect(BetterBitterStatusEffects.FEAR)

        // If the player can get more scared, increase the Fear level.
        // Otherwise, start a Hunt.
        if (amplifier <= FearStatusEffect.MAX_AMPLIFIER) {
            entity.addStatusEffect(
                StatusEffectInstance(
                    BetterBitterStatusEffects.FEAR,
                    FearPotion.calculateDuration(amplifier + 1),
                    (amplifier + 1).coerceAtMost(FearStatusEffect.MAX_AMPLIFIER),
                    true,
                    false,
                    false
                )
            )
        } else {
            entity.addStatusEffect(
                StatusEffectInstance(
                    BetterBitterStatusEffects.HUNT_MARK,
                    20 * 60 * 3,
                    0,
                    true,
                    false,
                    false
                )
            )
        }
    }

    @JvmStatic
    fun sendRandomInsanityMessage(entity: LivingEntity) {
        entity.sendMessage(Text.of(TextHelper.randomInsanityMessage()))
    }

    @JvmStatic
    fun setDrunkenness(entity: LivingEntity, drunkenness: Int) {
        // Remove Drunkenness.
        entity.removeStatusEffect(BetterBitterStatusEffects.DRUNKENNESS)

        // Apply Drunkenness.
        applyDrunkenness(entity, drunkenness)
    }

    @JvmStatic
    fun applyDrunkenness(entity: LivingEntity, drunkenness: Int) {
        if (drunkenness <= 0)
            return

        val amplifier: Int = (drunkenness - 1).coerceAtMost(DrunkennessStatusEffect.MAX_AMPLIFIER)

        // Apply Drunkenness.
        entity.addStatusEffect(
            StatusEffectInstance(
                BetterBitterStatusEffects.DRUNKENNESS,
                AlePotion.calculateDuration(amplifier),
                amplifier,
                true,
                false,
                false
            )
        )
    }

    @JvmStatic
    fun shouldDematerializeDownward(entity: LivingEntity): Boolean {
        return false
    }
}
