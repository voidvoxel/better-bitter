package dev.voidvoxel.betterbitter.potion

import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import dev.voidvoxel.betterbitter.statuseffect.DematerializationStatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

class DematerializationPotion(isExtended: Boolean = false) : Potion(
    StatusEffectInstance(
        BetterBitterStatusEffects.DEMATERIALIZATION,
        if (isExtended) DematerializationStatusEffect.EXTENDED_DURATION else DematerializationStatusEffect.BASE_DURATION,
        0
    )
) {
    companion object {
        @JvmStatic
        val DEMATERIALIZATION_POTION: DematerializationPotion = DematerializationPotion()
        @JvmStatic
        val DEMATERIALIZATION_POTION_EXTENDED: DematerializationPotion = DematerializationPotion(true)
        @JvmStatic
        var DEMATERIALIZATION_POTION_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var DEMATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null

        fun initialize() {
            // Register potions.
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "dematerialization"), DEMATERIALIZATION_POTION)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "dematerialization_extended"), DEMATERIALIZATION_POTION_EXTENDED)

            // Assign potion registry entries.
            DEMATERIALIZATION_POTION_REGISTRY_ENTRY = Registries.POTION.getEntry(DEMATERIALIZATION_POTION)
            DEMATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY = Registries.POTION.getEntry(DEMATERIALIZATION_POTION_EXTENDED)
        }
    }
}