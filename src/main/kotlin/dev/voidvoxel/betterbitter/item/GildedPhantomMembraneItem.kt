package dev.voidvoxel.betterbitter.item

import net.fabricmc.fabric.api.item.v1.EnchantingContext
import net.minecraft.enchantment.Enchantment
import net.minecraft.item.BookItem
import net.minecraft.item.EnchantedBookItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.item.ToolMaterials
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.Rarity

class GildedPhantomMembraneItem : BookItem(SETTINGS) {
    companion object {
        @JvmStatic
        val GILDED_PHANTOM_MEMBRANE_ITEM = GildedPhantomMembraneItem()

        @JvmStatic
        val GILDED_PHANTOM_MEMBRANE_ITEM_STACK = ItemStack(GILDED_PHANTOM_MEMBRANE_ITEM)

        @JvmStatic
        val SETTINGS: Settings
            get() {
                val settings = Settings()

                settings.rarity(Rarity.UNCOMMON)
                settings.maxDamage(10)

                return settings
            }

        @JvmStatic
        fun initialize() {
            Registry.register(Registries.ITEM, Identifier.of("betterbitter", "gilded_phantom_membrane"), GILDED_PHANTOM_MEMBRANE_ITEM)
        }
    }

    override fun getName(): Text {
        return Text.of("Gilded Phantom Membrane")
    }

    override fun getItemBarColor(stack: ItemStack?): Int {
        return 0x0069ff
    }
}
