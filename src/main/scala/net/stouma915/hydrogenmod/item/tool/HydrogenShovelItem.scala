package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, ShovelItem}
import net.stouma915.hydrogenmod.material.tool.HydrogenTier

object HydrogenShovelItem {

  private val instance = new HydrogenShovelItem

  def apply(): Item = instance

}

final class HydrogenShovelItem private ()
    extends ShovelItem(HydrogenTier(), 1.0f, 1.0f, new Item.Properties())
