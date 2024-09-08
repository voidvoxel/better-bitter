package dev.voidvoxel.betterbitter.entity.mob

import dev.voidvoxel.betterbitter.BetterBitter
import dev.voidvoxel.betterbitter.api.EntityHelper
import dev.voidvoxel.betterbitter.item.GildedPhantomMembraneItem
import dev.voidvoxel.betterbitter.potion.FearPotion
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import dev.voidvoxel.betterbitter.statuseffect.HuntMarkStatusEffect
import dev.voidvoxel.betterbitter.statuseffect.UnnervingStatusEffect
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.entity.*
import net.minecraft.entity.ai.TargetPredicate
import net.minecraft.entity.ai.brain.task.SonicBoomTask
import net.minecraft.entity.ai.goal.GoToWalkTargetGoal
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.WardenEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.predicate.entity.EntityPredicates
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Vec3i
import net.minecraft.world.Difficulty
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import kotlin.math.roundToInt

class GildedPhantomEntity(entityType: EntityType<LivingEntity>, world: World) : WardenEntity(EntityType.WARDEN, world) {
    private var globalDifficulty: Difficulty = Difficulty.NORMAL
    private var localDifficulty: Float = 0.0f

    companion object {
        @JvmStatic
        val ENTITY_TYPE: EntityType<LivingEntity> = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(BetterBitter.MOD_ID, "gilded_phantom"),
            EntityType.Builder.create(
                ::GildedPhantomEntity,
                SpawnGroup.CREATURE).dimensions(0.75f, 0.75f).build("cube")
        )

        @JvmStatic
        val HUNT_RADIUS: Int = 16

        @JvmStatic
        fun createBetterBitterMobAttributes(): DefaultAttributeContainer.Builder {
            return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MAX_ABSORPTION, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0)
        }

        @JvmStatic
        fun initialize() {
            FabricDefaultAttributeRegistry.register(
                ENTITY_TYPE,
                createBetterBitterMobAttributes()
            )
        }
    }

    override fun initialize(
        world: ServerWorldAccess?,
        difficulty: LocalDifficulty?,
        spawnReason: SpawnReason?,
        entityData: EntityData?
    ): EntityData? {
        val initializedEntityData = super.initialize(world, difficulty, spawnReason, entityData)

        if (difficulty != null) {
            this.localDifficulty = difficulty.localDifficulty
            this.globalDifficulty = difficulty.globalDifficulty
        }

        this.setDespawnCounter(HuntMarkStatusEffect.BASE_DURATION)

        return initializedEntityData
    }

    private fun repositionAroundTarget() {
        // Center around this Gilded Phantom.
        var x: Double = this.blockX.toDouble()
        var y: Double = this.blockY.toDouble()
        var z: Double = this.blockZ.toDouble()

        if (this.target != null) {
            // Center around the target.
            x = this.target!!.blockX.toDouble()
            y = this.target!!.blockY.toDouble()
            z = this.target!!.blockZ.toDouble()

            // Scare the target.
            EntityHelper.scare(this.target!!)
        }

        // Unnerve all nearby entities.
        this.unnerveNearbyEntities()

        // Select a random position around the target.
        x += HUNT_RADIUS * 2 * Math.random()  - HUNT_RADIUS
        y += HUNT_RADIUS * Math.random()
        z += HUNT_RADIUS * 2 * Math.random() - HUNT_RADIUS

        // Teleport where it's supposed to reposition to.
        this.updatePosition(0.0, -128.0, 0.0)

        // If the entity is inside a block, reposition around the target.
        if (EntityHelper.isVisionCovered(this))
            this.repositionAroundTarget()
    }

    private fun unnerveNearbyEntities() {
        val eyePosition = this.eyePos
        val world = this.world

        val amplifier: Int = 0

        val radius = UnnervingStatusEffect.BASE_RADIUS + UnnervingStatusEffect.BASE_RADIUS * amplifier

        val x1 = eyePosition.x - radius
        val y1 = eyePosition.y - radius
        val z1 = eyePosition.z - radius
        val x2 = eyePosition.x + radius
        val y2 = eyePosition.y + radius
        val z2 = eyePosition.z + radius

        val nearbyEntities = world.getEntitiesByClass(
            LivingEntity::class.java,
            Box(x1, y1, z1, x2, y2, z2),
            EntityPredicates.VALID_LIVING_ENTITY
        )

        EntityHelper.playAmbientHuntingSound(this)

        for (nearbyEntity in nearbyEntities) {
            if (nearbyEntity == this || nearbyEntity.equals(this))
                continue

            if (nearbyEntity is PassiveEntity)
                continue

            if (nearbyEntity !is HostileEntity) {
                val isTargetingEntity = nearbyEntity.isTarget(this, TargetPredicate.DEFAULT)

                if (!isTargetingEntity)
                    continue
            }

            if (!nearbyEntity.hasStatusEffect(BetterBitterStatusEffects.FEAR)) {
                nearbyEntity.addStatusEffect(
                    StatusEffectInstance(
                        BetterBitterStatusEffects.FEAR,
                        FearPotion.calculateDuration(amplifier),
                        amplifier
                    ),
                    this
                )
            }
        }
    }

    private fun shouldDespawn(): Boolean {
        return this.health <= 0
    }

    override fun cannotDespawn(): Boolean {
        if (this.shouldDespawn())
            return true

        return false
    }

    override fun getDespawnCounter(): Int {
        if (this.shouldDespawn())
            return HuntMarkStatusEffect.BASE_DURATION

        return super.getDespawnCounter()
    }

    override fun canHaveStatusEffect(effect: StatusEffectInstance?): Boolean {
        if (effect == null)
            return false

        if (effect.effectType.value().equals(BetterBitterStatusEffects.FEAR)) {
            return this.hasStatusEffect(BetterBitterStatusEffects.MATERIALIZATION)
        }

        if (effect.effectType.value().equals(BetterBitterStatusEffects.HUNT_MARK)) {
            return false
        }

        return true
    }

    override fun tick() {
        if (this.hasStatusEffect(BetterBitterStatusEffects.MATERIALIZATION)) {
            // Disable the sonic boom attack.
            SonicBoomTask.cooldown(this, 0)
        } else {
            // Disable the sonic boom attack.
            SonicBoomTask.cooldown(this, 20 * 60 * 60 * 24 * 7)
        }

        if (!this.hasStatusEffect(BetterBitterStatusEffects.DEMATERIALIZATION)) {
            this.addStatusEffect(
                StatusEffectInstance(
                    BetterBitterStatusEffects.DEMATERIALIZATION,
                    StatusEffectInstance.INFINITE,
                    0,
                    true,
                    false,
                    false
                )
            )
        }

        if (target != null && target!!.canSee(this)) {
            this.repositionAroundTarget()
        } else if (Math.random() <= 0.001)
            this.repositionAroundTarget()

        if (Math.random() <= 0.001 || this.world.time % 160 == 0L)
            this.unnerveNearbyEntities()

        super.tick()
    }

    override fun getSoundPitch(): Float {
        // If the Gilded Phantom has found a target or has not already scared its target:
        if (target != null && !(target!!.hasStatusEffect(BetterBitterStatusEffects.FEAR)))
            return 0.9f + 0.2f * Math.random().toFloat()

        return 0.5f + 0.1f * Math.random().toFloat()
    }

    override fun getSoundVolume(): Float {
        if (target != null && !(target!!.hasStatusEffect(BetterBitterStatusEffects.FEAR))) {
            return 0.0f
        }

        return super.getSoundVolume()
    }

    override fun getAmbientSound(): SoundEvent? {
        if (target == null || target!!.hasStatusEffect(BetterBitterStatusEffects.FEAR))
            return SoundEvent.of(
                Identifier.of("minecraft", "item.goat_horn.sound.6")
            )

        if (target != null || target!!.hasStatusEffect(BetterBitterStatusEffects.HUNT_MARK))
            return SoundEvent.of(
                Identifier.of("minecraft", "entity.warden.agitated")
            )

        if (target!!.blockY <= 48) {
            return SoundEvent.of(
                Identifier.of("minecraft", "entity.glow_squid.ambient")
            )
        } else {
            return SoundEvent.of(
                Identifier.of("minecraft", "entity.wandering_trader.ambient")
            )
        }
    }

    override fun getDefaultName(): Text {
        return Text.of("Gilded Phantom")
    }

    override fun onDamaged(damageSource: DamageSource?) {
        this.repositionAroundTarget()

        super.onDamaged(damageSource)
    }

    override fun dropLoot(damageSource: DamageSource?, causedByPlayer: Boolean) {
        if (!causedByPlayer) {
            val entity = GildedPhantomEntity(ENTITY_TYPE, world)

            entity.setPosition(this.x, this.y, this.z)
        } else {
            this.dropItem(GildedPhantomMembraneItem())
        }
    }
}