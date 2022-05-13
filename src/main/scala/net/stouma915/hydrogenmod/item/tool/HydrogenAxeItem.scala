package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{AxeItem, Item}
import net.stouma915.hydrogenmod.material.tool.HydrogenTier

object HydrogenAxeItem {

  private val instance = new HydrogenAxeItem

  def apply(): Item = instance

}

final class HydrogenAxeItem private ()
    extends AxeItem(HydrogenTier(), 1.0f, 1.0f, new Item.Properties())
