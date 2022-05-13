package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, SwordItem}
import net.stouma915.hydrogenmod.material.tool.OxygenTier

object OxygenSwordItem {

  private val instance = new OxygenSwordItem

  def apply(): Item = instance

}

final class OxygenSwordItem private ()
    extends SwordItem(OxygenTier(), 0, 1.0f, new Item.Properties())
