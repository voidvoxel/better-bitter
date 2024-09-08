package dev.voidvoxel.betterbitter.potion

import dev.voidvoxel.betterbitter.statuseffect.FearStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

class FearPotion(isExtended: Boolean = false, amplifier: Int = 0) : Potion(
    StatusEffectInstance(
        BetterBitterStatusEffects.FEAR,
        calculateDuration(amplifier, isExtended),
        amplifier,
        true,
        false,
        false
    )
) {
    companion object {
        @JvmStatic
        val FEAR_POTION: FearPotion = FearPotion()
        @JvmStatic
        val FEAR_POTION_AMPLIFIED: FearPotion = FearPotion(false, 1)
        @JvmStatic
        val FEAR_POTION_EXTENDED: FearPotion = FearPotion(true)
        @JvmStatic
        var FEAR_POTION_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var FEAR_POTION_AMPLIFIED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var FEAR_POTION_EXTENDED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null

        @JvmStatic
        fun calculateDuration(amplifier: Int, isExtended: Boolean = false): Int {
            return if (isExtended) (FearStatusEffect.EXTENDED_DURATION / (amplifier + 1))
            else (FearStatusEffect.BASE_DURATION / (amplifier + 1))
        }

        @JvmStatic
        fun initialize() {
            // Register potions.
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "fear"), FEAR_POTION)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "fear_amplified"), FEAR_POTION_AMPLIFIED)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "fear_extended"), FEAR_POTION_EXTENDED)

            // Assign potion registry entries.
            FEAR_POTION_REGISTRY_ENTRY = Registries.POTION.getEntry(FEAR_POTION)
            FEAR_POTION_AMPLIFIED_REGISTRY_ENTRY = Registries.POTION.getEntry(FEAR_POTION_AMPLIFIED)
            FEAR_POTION_EXTENDED_REGISTRY_ENTRY = Registries.POTION.getEntry(FEAR_POTION_EXTENDED)
        }
    }
}