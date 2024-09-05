package dev.voidvoxel.betterbitter.api

import dev.voidvoxel.betterbitter.statuseffect.AncientKnowledgeStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import dev.voidvoxel.betterbitter.statuseffect.DematerializationStatusEffect
import net.minecraft.block.AirBlock
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3i

object EntityHelper {
    @JvmStatic
    fun hasAncientKnowledge(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            return entity.hasStatusEffect(BetterBitterStatusEffects.ANCIENT_KNOWLEDGE)
        }

        return false
    }

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
    fun isVisionCovered(entity: Entity): Boolean {
        if (entity is LivingEntity) {
            return entity.getWorld().getBlockState(BlockPos(Vec3i(Math.round(entity.eyePos.x).toInt(), Math.round(entity.eyePos.y).toInt(), Math.round(entity.eyePos.z).toInt()))).block !is AirBlock
        }

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
