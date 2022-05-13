package net.stouma915.hydrogenmod.item.tool

import net.minecraft.item.{Item, PickaxeItem}
import net.stouma915.hydrogenmod.material.tool.HydrogenTier

object HydrogenPickaxeItem {

  private val instance = new HydrogenPickaxeItem

  def apply(): Item = instance

}

final class HydrogenPickaxeItem private ()
    extends PickaxeItem(HydrogenTier(), 1, 1.0f, new Item.Properties())
