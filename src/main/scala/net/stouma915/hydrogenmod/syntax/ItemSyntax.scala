package net.stouma915.hydrogenmod.syntax

import net.minecraft.item.{Item, ItemStack}

trait ItemSyntax {

  implicit class ItemOps(item: Item) {

    def toGeneralItemStack: ItemStack = new ItemStack(item)

    def isHydrogenItem: Boolean = 
      HydrogenMod.HydrogenItems.contains(item)

    def isOxygenItem: Boolean =
      HydrogenMod.OxygenItems.contains(item)

  }

}
