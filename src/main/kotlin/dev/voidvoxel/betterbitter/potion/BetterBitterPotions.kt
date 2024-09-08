package dev.voidvoxel.betterbitter.potion

import net.minecraft.potion.Potion
import net.minecraft.registry.entry.RegistryEntry

class BetterBitterPotions {
    companion object {
        @JvmStatic
        var ALE: RegistryEntry<Potion>? = null
        @JvmStatic
        var ALE_AMPLIFIED: RegistryEntry<Potion>? = null
        @JvmStatic
        var ALE_EXTENDED: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_AMPLIFIED: RegistryEntry<Potion>? = null
        @JvmStatic
        var ANCIENT_KNOWLEDGE_EXTENDED: RegistryEntry<Potion>? = null
        @JvmStatic
        var FEAR: RegistryEntry<Potion>? = null
        @JvmStatic
        var FEAR_AMPLIFIED: RegistryEntry<Potion>? = null
        @JvmStatic
        var FEAR_EXTENDED: RegistryEntry<Potion>? = null
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
            AlePotion.initialize()
            AncientKnowledgePotion.initialize()
            FearPotion.initialize()
            DematerializationPotion.initialize()
            MaterializationPotion.initialize()

            ALE = AlePotion.ALE_POTION_REGISTRY_ENTRY
            ALE_AMPLIFIED = AlePotion.ALE_POTION_AMPLIFIED_REGISTRY_ENTRY
            ALE_EXTENDED = AlePotion.ALE_POTION_EXTENDED_REGISTRY_ENTRY
            ANCIENT_KNOWLEDGE = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_REGISTRY_ENTRY
            ANCIENT_KNOWLEDGE_AMPLIFIED = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_AMPLIFIED_REGISTRY_ENTRY
            ANCIENT_KNOWLEDGE_EXTENDED = AncientKnowledgePotion.ANCIENT_KNOWLEDGE_POTION_EXTENDED_REGISTRY_ENTRY
            FEAR = FearPotion.FEAR_POTION_REGISTRY_ENTRY
            FEAR_AMPLIFIED = FearPotion.FEAR_POTION_AMPLIFIED_REGISTRY_ENTRY
            FEAR_EXTENDED = FearPotion.FEAR_POTION_EXTENDED_REGISTRY_ENTRY
            DEMATERIALIZATION = DematerializationPotion.DEMATERIALIZATION_POTION_REGISTRY_ENTRY
            DEMATERIALIZATION_EXTENDED = DematerializationPotion.DEMATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY
            MATERIALIZATION = MaterializationPotion.MATERIALIZATION_POTION_REGISTRY_ENTRY
            MATERIALIZATION_EXTENDED = MaterializationPotion.MATERIALIZATION_POTION_EXTENDED_REGISTRY_ENTRY
        }
    }
}