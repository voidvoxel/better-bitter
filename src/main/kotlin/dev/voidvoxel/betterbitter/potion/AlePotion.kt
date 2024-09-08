package dev.voidvoxel.betterbitter.potion

import dev.voidvoxel.betterbitter.statuseffect.AncientKnowledgeStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
import kotlin.math.roundToInt

class AlePotion(isExtended: Boolean = false, amplifier: Int = 0) : Potion(
    StatusEffectInstance(
        BetterBitterStatusEffects.DRUNKENNESS,
        calculateDuration(amplifier, isExtended),
        amplifier
    ),
    StatusEffectInstance(
        StatusEffects.STRENGTH,
        calculateSecondaryDuration(amplifier, isExtended),
        amplifier
    )
) {
    companion object {
        @JvmStatic
        val ALE_POTION: AlePotion = AlePotion()
        @JvmStatic
        val ALE_POTION_AMPLIFIED: AlePotion = AlePotion(false, 1)
        @JvmStatic
        val ALE_POTION_EXTENDED: AlePotion = AlePotion(true)
        @JvmStatic
        var ALE_POTION_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var ALE_POTION_AMPLIFIED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var ALE_POTION_EXTENDED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null

        @JvmStatic
        fun calculateDuration(amplifier: Int, isExtended: Boolean = false): Int {
            return if (isExtended) (AncientKnowledgeStatusEffect.EXTENDED_DURATION / (amplifier + 1))
            else (AncientKnowledgeStatusEffect.BASE_DURATION / (amplifier + 1))
        }

        @JvmStatic
        fun calculateSecondaryDuration(amplifier: Int, isExtended: Boolean = false): Int {
            return (calculateDuration(amplifier, isExtended) * 0.25).roundToInt()
        }

        @JvmStatic
        fun initialize() {
            // Register potions.
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ale"), ALE_POTION)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ale_amplified"), ALE_POTION_AMPLIFIED)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ale_extended"), ALE_POTION_EXTENDED)

            // Assign potion registry entries.
            ALE_POTION_REGISTRY_ENTRY = Registries.POTION.getEntry(ALE_POTION)
            ALE_POTION_AMPLIFIED_REGISTRY_ENTRY = Registries.POTION.getEntry(ALE_POTION_AMPLIFIED)
            ALE_POTION_EXTENDED_REGISTRY_ENTRY = Registries.POTION.getEntry(ALE_POTION_EXTENDED)
        }
    }
}