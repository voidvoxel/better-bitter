package dev.voidvoxel.betterbitter

import dev.voidvoxel.betterbitter.item.BetterBitterItems
import dev.voidvoxel.betterbitter.potion.BetterBitterPotions
import dev.voidvoxel.betterbitter.recipe.BrewingRecipes
import dev.voidvoxel.betterbitter.statuseffect.BetterBitterStatusEffects
import net.fabricmc.api.ModInitializer

class BetterBitter : ModInitializer {
    override fun onInitialize() {
        BetterBitterStatusEffects.initialize()
        BetterBitterItems.initialize()
        BetterBitterPotions.initialize()
        BrewingRecipes.registerAll()
    }
}
