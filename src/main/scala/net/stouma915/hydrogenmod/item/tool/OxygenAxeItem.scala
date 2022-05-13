package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{AxeItem, Item}
import net.stouma915.hydrogenmod.material.tool.OxygenTier

object OxygenAxeItem {

  private val instance = new OxygenAxeItem

  def apply(): Item = instance

}

final class OxygenAxeItem private ()
    extends AxeItem(OxygenTier(), 1.0f, 1.0f, new Item.Properties())
