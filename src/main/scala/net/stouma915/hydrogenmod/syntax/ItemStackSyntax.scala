package  net.stouma915.hydrogenmod.syntax

import net.minecraft.item.ItemStack
import net.stouma915.hydrogenmod.HydrogenMod

trait ItemStackSyntax {

  implicit class ItemStackOps(itemStack: ItemStack) {

    import net.stouma915.hydrogenmod.syntax.ItemSyntax._

    def toGeneralItemStack: ItemStack =
      itemStack.getItem.toGeneralItemStack

    def isHydrogenItem: Boolean = 
      itemStack.getItem.isHydrogenItem

    def isOxygenItem: Boolean =
      itemStack.getItem.isOxygenItem

  }

}
