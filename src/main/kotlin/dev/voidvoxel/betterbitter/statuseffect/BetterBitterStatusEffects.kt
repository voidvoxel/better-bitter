package dev.voidvoxel.betterbitter.statuseffect

import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.entry.RegistryEntry

object BetterBitterStatusEffects {
    @JvmStatic
    var ANCIENT_KNOWLEDGE: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var FEAR: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var DEMATERIALIZATION: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var DRUNKENNESS: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var HUNT_MARK: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var MATERIALIZATION: RegistryEntry<StatusEffect>? = null
    @JvmStatic
    var UNNERVING: RegistryEntry<StatusEffect>? = null

    @JvmStatic
    fun initialize() {
        AncientKnowledgeStatusEffect.initialize()
        FearStatusEffect.initialize()
        DematerializationStatusEffect.initialize()
        DrunkennessStatusEffect.initialize()
        HuntMarkStatusEffect.initialize()
        MaterializationStatusEffect.initialize()
        UnnervingStatusEffect.initialize()

        // Ancient Knowledge
        ANCIENT_KNOWLEDGE = AncientKnowledgeStatusEffect.ANCIENT_KNOWLEDGE_STATUS_EFFECT_REGISTRY_ENTRY
        // Fear
        FEAR = FearStatusEffect.FEAR_STATUS_EFFECT_REGISTRY_ENTRY
        // Dematerialization
        DEMATERIALIZATION = DematerializationStatusEffect.DEMATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY
        // Drunkenness
        DRUNKENNESS = DrunkennessStatusEffect.DRUNKENNESS_STATUS_EFFECT_REGISTRY_ENTRY
        // Hunt Mark
        HUNT_MARK = HuntMarkStatusEffect.HUNT_MARK_STATUS_EFFECT_REGISTRY_ENTRY
        // Materialization
        MATERIALIZATION = MaterializationStatusEffect.MATERIALIZATION_STATUS_EFFECT_REGISTRY_ENTRY
        // Unnerving
        UNNERVING = UnnervingStatusEffect.UNNERVING_STATUS_EFFECT_REGISTRY_ENTRY
    }
}