package dev.voidvoxel.betterbitter.recipe

import dev.voidvoxel.betterbitter.item.BetterBitterItems
import dev.voidvoxel.betterbitter.potion.BetterBitterPotions
import dev.voidvoxel.betterbitter.potion.DematerializationPotion
import dev.voidvoxel.betterbitter.potion.MaterializationPotion
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder
import net.minecraft.item.Items
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry

class BrewingRecipes {
    companion object {
        @JvmStatic
        fun registerAll() {
            // Register all brewing recipes.
            FabricBrewingRecipeRegistryBuilder.BUILD.register { builder ->
                this.buildAllRecipes(builder)
            }
        }

        private fun buildAllRecipes(builder: BrewingRecipeRegistry.Builder) {
            // Awkward Potion
            this.buildRecipesForAwkwardPotion(builder)
            // Thick Potion
            this.buildRecipesForThickPotion(builder)
            // Regeneration Potion
            this.buildRecipesForRegenerationPotion(builder)
            // Elixir of Ancient Knowledge
            this.buildRecipesForAncientKnowledgePotion(builder)
            // Elixir of Dematerialization
            this.buildRecipesForDematerializationPotion(builder)
            // Elixir of Materialization
            this.buildRecipesForMaterializationPotion(builder)
        }

        private fun buildRecipesForAwkwardPotion(builder: BrewingRecipeRegistry.Builder) {
            // Water Bottle + Brown Mushroom -> Awkward Potion
            builder.registerPotionRecipe(
                Potions.WATER,
                Items.BROWN_MUSHROOM,
                Potions.AWKWARD
            )
        }

        private fun buildRecipesForThickPotion(builder: BrewingRecipeRegistry.Builder) {
            // Water Bottle + Glowstone Dust -> Thick Potion
            builder.registerPotionRecipe(
                Potions.WATER,
                Items.GLOWSTONE_DUST,
                Potions.THICK
            )

            // Water Bottle + Red Mushroom -> Thick Potion
            builder.registerPotionRecipe(
                Potions.WATER,
                Items.RED_MUSHROOM,
                Potions.THICK
            )
        }

        private fun buildRecipesForAncientKnowledgePotion(builder: BrewingRecipeRegistry.Builder) {
            // Thick Potion + Enchanted Phantom Membrane -> Elixir of Ancient Knowledge
            builder.registerPotionRecipe(
                Potions.THICK,
                BetterBitterItems.ENCHANTED_PHANTOM_MEMBRANE,
                BetterBitterPotions.ANCIENT_KNOWLEDGE
            )

            // Elixir of Ancient Knowledge + Glowstone Dust -> Elixir of Ancient Knowledge II
            builder.registerPotionRecipe(
                BetterBitterPotions.ANCIENT_KNOWLEDGE,
                Items.GLOWSTONE_DUST,
                BetterBitterPotions.ANCIENT_KNOWLEDGE_AMPLIFIED
            )

            // Elixir of Ancient Knowledge + Redstone Dust -> Elixir of Ancient Knowledge (Extended)
            builder.registerPotionRecipe(
                BetterBitterPotions.ANCIENT_KNOWLEDGE,
                Items.REDSTONE,
                BetterBitterPotions.ANCIENT_KNOWLEDGE_EXTENDED
            )
        }

        private fun buildRecipesForDematerializationPotion(builder: BrewingRecipeRegistry.Builder) {
            // Elixir of Materialization + Fermented Spider Eye -> Elixir of Dematerialization
            builder.registerPotionRecipe(
                BetterBitterPotions.MATERIALIZATION,
                Items.FERMENTED_SPIDER_EYE,
                BetterBitterPotions.DEMATERIALIZATION
            )

            // Elixir of Dematerialization + Redstone Dust -> Elixir of Dematerialization (Extended)
            builder.registerPotionRecipe(
                BetterBitterPotions.DEMATERIALIZATION,
                Items.REDSTONE,
                BetterBitterPotions.DEMATERIALIZATION_EXTENDED
            )
        }

        private fun buildRecipesForMaterializationPotion(builder: BrewingRecipeRegistry.Builder) {
            // Thick Potion + Gilded Phantom Membrane -> Elixir of Materialization
            builder.registerPotionRecipe(
                Potions.THICK,
                BetterBitterItems.GILDED_PHANTOM_MEMBRANE,
                BetterBitterPotions.MATERIALIZATION
            )

            // Elixir of Materialization + Redstone Dust -> Elixir of Materialization (Extended)
            builder.registerPotionRecipe(
                BetterBitterPotions.MATERIALIZATION,
                Items.REDSTONE,
                BetterBitterPotions.MATERIALIZATION_EXTENDED
            )
        }

        private fun buildRecipesForRegenerationPotion(builder: BrewingRecipeRegistry.Builder) {
            // Instant Health Potion + Redstone Dust -> Regeneration Potion
            builder.registerPotionRecipe(
                Potions.HEALING,
                Items.REDSTONE,
                Potions.REGENERATION
            )

            // Instant Health Potion II + Redstone Dust -> Regeneration II Potion
            builder.registerPotionRecipe(
                Potions.STRONG_HEALING,
                Items.REDSTONE,
                Potions.STRONG_REGENERATION
            )
        }
    }
}
