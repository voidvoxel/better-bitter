package dev.voidvoxel.betterbitter.item

class BetterBitterItems {
    companion object {
        @JvmStatic
        val ENCHANTED_PHANTOM_MEMBRANE = EnchantedPhantomMembraneItem.ENCHANTED_PHANTOM_MEMBRANE_ITEM
        @JvmStatic
        val GILDED_PHANTOM_MEMBRANE = GildedPhantomMembraneItem.GILDED_PHANTOM_MEMBRANE_ITEM

        @JvmStatic
        fun initialize() {
            EnchantedPhantomMembraneItem.initialize()
            GildedPhantomMembraneItem.initialize()
        }
    }
}