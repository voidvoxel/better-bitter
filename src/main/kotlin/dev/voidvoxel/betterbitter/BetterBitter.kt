package dev.voidvoxel.betterbitter

import dev.voidvoxel.betterbitter.entity.mob.BetterBitterEntities
import dev.voidvoxel.betterbitter.item.BetterBitterItems
import dev.voidvoxel.betterbitter.potion.BetterBitterPotions
import dev.voidvoxel.betterbitter.recipe.BrewingRecipes
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import net.fabricmc.api.ModInitializer

class BetterBitter : ModInitializer {
    companion object {
        @JvmStatic
        val MOD_ID: String = "betterbitter"
    }

    override fun onInitialize() {
        // Register status effects.
        BetterBitterStatusEffects.initialize()

        // Register items.
        BetterBitterItems.initialize()

        // Register potions.
        BetterBitterPotions.initialize()
        BrewingRecipes.registerAll()

        // Register entities.
        BetterBitterEntities.initialize()
    }
}
