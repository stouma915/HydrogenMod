package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{HoeItem, Item}
import net.stouma915.hydrogenmod.material.tool.OxygenTier

object OxygenHoeItem {

  private val instance = new OxygenHoeItem

  def apply(): Item = instance

}

final class OxygenHoeItem private ()
    extends HoeItem(OxygenTier(), 1, 1.0f, new Item.Properties())
