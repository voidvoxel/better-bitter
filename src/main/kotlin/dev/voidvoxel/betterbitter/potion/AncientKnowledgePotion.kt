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

class AncientKnowledgePotion(isExtended: Boolean = false, amplifier: Int = 0) : Potion(
    StatusEffectInstance(
        StatusEffects.ABSORPTION,
        calculateDuration(amplifier, isExtended),
        amplifier
    ),
    StatusEffectInstance(
        BetterBitterStatusEffects.ANCIENT_KNOWLEDGE,
        calculateDuration(amplifier, isExtended),
        amplifier
    )
) {
    companion object {
        @JvmStatic
        val ANCIENT_KNOWLEDGE_POTION: AncientKnowledgePotion = AncientKnowledgePotion()
        @JvmStatic
        val ANCIENT_KNOWLEDGE_POTION_AMPLIFIED: AncientKnowledgePotion = AncientKnowledgePotion(false, 1)
        @JvmStatic
        val ANCIENT_KNOWLEDGE_POTION_EXTENDED: AncientKnowledgePotion = AncientKnowledgePotion(true)
        @JvmStatic
        var ANCIENT_KNOWLEDGE_POTION_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_POTION_AMPLIFIED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_POTION_EXTENDED_REGISTRY_ENTRY: RegistryEntry<Potion>? = null

        @JvmStatic
        fun calculateDuration(amplifier: Int, isExtended: Boolean = false): Int {
            return if (isExtended) (AncientKnowledgeStatusEffect.EXTENDED_DURATION / (amplifier + 1))
            else (AncientKnowledgeStatusEffect.BASE_DURATION / (amplifier + 1))
        }

        @JvmStatic
        fun initialize() {
            // Register potions.
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ancient_knowledge"), ANCIENT_KNOWLEDGE_POTION)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ancient_knowledge_amplified"), ANCIENT_KNOWLEDGE_POTION_AMPLIFIED)
            Registry.register(Registries.POTION, Identifier.of("betterbitter", "ancient_knowledge_extended"), ANCIENT_KNOWLEDGE_POTION_EXTENDED)

            // Assign potion registry entries.
            ANCIENT_KNOWLEDGE_POTION_REGISTRY_ENTRY = Registries.POTION.getEntry(ANCIENT_KNOWLEDGE_POTION)
            ANCIENT_KNOWLEDGE_POTION_AMPLIFIED_REGISTRY_ENTRY = Registries.POTION.getEntry(ANCIENT_KNOWLEDGE_POTION_AMPLIFIED)
            ANCIENT_KNOWLEDGE_POTION_EXTENDED_REGISTRY_ENTRY = Registries.POTION.getEntry(ANCIENT_KNOWLEDGE_POTION_EXTENDED)
        }
    }
}