package dev.voidvoxel.betterbitter.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

class EnchantedPhantomMembraneItem : Item(SETTINGS) {
    companion object {
        @JvmStatic
        val ENCHANTED_PHANTOM_MEMBRANE_ITEM = EnchantedPhantomMembraneItem()

        @JvmStatic
        val SETTINGS: Settings
            get() {
                val settings = Settings()

                settings.rarity(Rarity.RARE)

                return settings
            }

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.ITEM, Identifier.of("betterbitter", "enchanted_phantom_membrane"), ENCHANTED_PHANTOM_MEMBRANE_ITEM)
        }
    }

    override fun getName(): Text {
        return Text.of("§eEnchanted Phantom Membrane§r")
    }

    override fun hasGlint(stack: ItemStack?): Boolean {
        return true
    }

    override fun getRecipeRemainder(stack: ItemStack?): ItemStack {
        if (stack == null)
            return ItemStack(Items.AIR)

        val count = stack.count

        val remainder = ItemStack(
            BetterBitterItems.GILDED_PHANTOM_MEMBRANE,
            count
        )

        return remainder
    }
}