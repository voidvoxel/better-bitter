package dev.voidvoxel.betterbitter.statuseffect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.entry.RegistryEntry

class BetterBitterStatusEffects {
    companion object {
        @JvmStatic
        var ANCIENT_KNOWLEDGE: RegistryEntry<StatusEffect>? = null
        @JvmStatic
        var DEMATERIALIZATION: RegistryEntry<StatusEffect>? = null
        @JvmStatic
        var MATERIALIZATION: RegistryEntry<StatusEffect>? = null

        @JvmStatic
        fun initialize() {
            AncientKnowledgeStatusEffect.initialize()
            DematerializationStatusEffect.initialize()
            MaterializationStatusEffect.initialize()

            ANCIENT_KNOWLEDGE = AncientKnowledgeStatusEffect.ANCIENT_KNOWLEDGE_STATUS_EFFECT_REGISTRY_ENTRY
            DEMATERIALIZATION = DematerializationStatusEffect.DEMATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY
            MATERIALIZATION = MaterializationStatusEffect.MATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY
        }
    }
}