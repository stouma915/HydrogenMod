package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, PickaxeItem}
import net.stouma915.hydrogenmod.material.tool.OxygenTier

object OxygenPickaxeItem {

  private val instance = new OxygenPickaxeItem

  def apply(): Item = instance

}

final class OxygenPickaxeItem private ()
    extends PickaxeItem(OxygenTier(), 1, 1.0f, new Item.Properties())
