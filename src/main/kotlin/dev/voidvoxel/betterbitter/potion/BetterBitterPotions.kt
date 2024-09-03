package dev.voidvoxel.betterbitter.potion

import net.minecraft.potion.Potion
import net.minecraft.registry.entry.RegistryEntry

class BetterBitterPotions {
    companion object {
        @JvmStatic
        var ANCIENT_KNOWLEDGE: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_AMPLIFIED: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_EXTENDED: RegistryEntry<Potion>? = null
        @JvmStatic
        var DEMATERIALIZATION: RegistryEntry<Potion>? = null
        @JvmStatic
        var DEMATERIALIZATION_EXTENDED: RegistryEntry<Potion>? = null
        @JvmStatic
        var MATERIALIZATION: RegistryEntry<Potion>? = null
        @JvmStatic
        var MATERIALIZATION_EXTENDED: RegistryEntry<Potion>? = null

        @JvmStatic
        fun initialize() {
            AncientKnowledgePotion.initialize()
            DematerializationPotion.initialize()
            MaterializationPotion.initialize()

            ANCIENT_KNOWLEDGE = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_REGISTRY_ENTRY
            ANCIENT_KNOWLEDGE_AMPLIFIED = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_AMPLIFIED_REGISTRY_ENTRY
            ANCIENT_KNOWLEDGE_EXTENDED = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_EXTENDED_REGISTRY_ENTRY
            DEMATERIALIZATION = DematerializationPotion.DEMATERIALIZATION_POTION_REGISTRY_ENTRY
            DEMATERIALIZATION_EXTENDED = DematerializationPotion.DEMATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY
            MATERIALIZATION = MaterializationPotion.MATERIALIZATION_POTION_REGISTRY_ENTRY
            MATERIALIZATION_EXTENDED = MaterializationPotion.MATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY
        }
    }
}