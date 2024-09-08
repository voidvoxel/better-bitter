package dev.voidvoxel.betterbitter.entity.mob

import dev.voidvoxel.betterbitter.BetterBitter
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object BetterBitterEntities {
    @JvmStatic
    val GILDED_PHANTOM: EntityType<LivingEntity> = GildedPhantomEntity.ENTITY_TYPE

    @JvmStatic
    fun initialize() {
        GildedPhantomEntity.initialize()
    }
}