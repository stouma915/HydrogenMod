package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{HoeItem, Item}
import net.stouma915.hydrogenmod.material.tool.HydrogenTier

object HydrogenHoeItem {

  private val instance = new HydrogenHoeItem

  def apply(): Item = instance

}

final class HydrogenHoeItem private ()
    extends HoeItem(HydrogenTier(), 1, 1.0f, new Item.Properties())
