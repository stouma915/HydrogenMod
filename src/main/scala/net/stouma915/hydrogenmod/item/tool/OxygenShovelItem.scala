package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, ShovelItem}
import net.stouma915.hydrogenmod.material.tool.OxygenTier

object OxygenShovelItem {

  private val instance = new OxygenShovelItem

  def apply(): Item = instance

}

final class OxygenShovelItem private ()
    extends ShovelItem(OxygenTier(), 1.0f, 1.0f, new Item.Properties())
