package dev.voidvoxel.betterbitter.api

import dev.voidvoxel.betterbitter.statuseffect.DematerializationStatusEffect
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity

object EntityHelper {
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
    fun shouldDematerializeDownward(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            return entity.isSneaking
        }

        return false
    }
}
