package net.stouma915.hydrogenmod.syntax

import net.minecraft.item.ItemStack
import net.stouma915.hydrogenmod.HydrogenMod

trait ItemStackSyntax {

  implicit class ItemStackOps(itemStack: ItemStack) {

    def makeStack: ItemStack =
      new ItemStack(itemStack.getItem)

    def isHydrogenItem: Boolean =
      HydrogenMod.HydrogenItems.contains(itemStack.getItem)

    def isOxygenItem: Boolean =
      HydrogenMod.OxygenItems.contains(itemStack.getItem)

    def act(func: ItemStack => Unit): ItemStack = {
      func(itemStack)
      itemStack
    }

  }

}
