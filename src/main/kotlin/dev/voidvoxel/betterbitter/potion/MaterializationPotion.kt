package dev.voidvoxel.betterbitter.potion

import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import dev.voidvoxel.betterbitter.statuseffect.DematerializationStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.MaterializationStatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.potion.Potion
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

class MaterializationPotion(isExtended: Boolean = false) : Potion(
    StatusEffectInstance(
        BetterBitterStatusEffects.MATERIALIZATION,
        if (isExtended) DematerializationStatusEffect.EXTENDED_DURATION else DematerializationStatusEffect.BASE_DURATION,
        0
    )
) {
    companion object {
        @JvmStatic
        val MATERIALIZATION_POTION: MaterializationPotion = MaterializationPotion()
        @JvmStatic
        val MATERIALIZATION_POTION_EXTENDED: MaterializationPotion = MaterializationPotion(true)
        @JvmStatic
        var MATERIALIZATION_POTION_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var MATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null

        fun initialize() {
            // Register potions.
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "materialization"), MATERIALIZATION_POTION)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "materialization_extended"), MATERIALIZATION_POTION_EXTENDED)

            // Assign potion registry entries.
            MATERIALIZATION_POTION_REGISTRY_ENTRY = Registries.POTION.getEntry(MATERIALIZATION_POTION)
            MATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY = Registries.POTION.getEntry(MATERIALIZATION_POTION_EXTENDED)
        }
    }
}