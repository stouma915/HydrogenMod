package net.stouma915.hydrogenmod.syntax

import net.minecraft.item.{Items, ItemStack}
import net.stouma915.hydrogenmod.HydrogenMod

trait ItemStackSyntax {

  implicit class ItemStackOps(itemStack: ItemStack) {

    def makeStack: ItemStack =
      new ItemStack(itemStack.getItem)

    def isHydrogenItem: Boolean =
      HydrogenMod.HydrogenItems.contains(itemStack.getItem)

    def isOxygenItem: Boolean =
      HydrogenMod.OxygenItems.contains(itemStack.getItem)

    def isAir: Boolean =
      itemStack.nonNull && itemStack.getItem eq Items.AIR

  }

}
