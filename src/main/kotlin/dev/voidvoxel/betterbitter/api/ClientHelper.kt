package dev.voidvoxel.betterbitter.api

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity

class ClientHelper {
    companion object {
        @JvmStatic
        fun getName(entity: LivingEntity): String {
            if (entity is PlayerEntity)
                return getUsername(entity)

            return entity.name.content.toString()
        }

        @JvmStatic
        fun getUsername(entity: PlayerEntity): String {
            return entity.name.content.toString()
        }

        @JvmStatic
        fun stopAllSounds(player: PlayerEntity) {
//            MinecraftClient.getInstance().soundManager.stopAll()
//            player.world.server!!.commandManager.executeWithPrefix(
//                player.commandSource,
//                "stopsound"
//            )
        }

        @JvmStatic
        fun stopMusic(player: PlayerEntity) {
//            player.world.server!!.commandManager.executeWithPrefix(
//                player.commandSource,
//                "stopsound @p music"
//            )
        }
    }
}