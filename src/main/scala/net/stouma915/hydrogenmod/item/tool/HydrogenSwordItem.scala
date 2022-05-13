package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, SwordItem}
import net.stouma915.hydrogenmod.material.tool.HydrogenTier

object HydrogenSwordItem {

  private val instance = new HydrogenSwordItem

  def apply(): Item = instance

}

final class HydrogenSwordItem private ()
    extends SwordItem(HydrogenTier(), 0, 1.0f, new Item.Properties())
