package net.stouma915.hydrogenmod.item

import net.minecraft.item.Item
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object HydrogenItem {

  private val instance = new HydrogenItem

  def apply(): Item = instance

}

final class HydrogenItem private ()
    extends Item(new Item.Properties().tab(HydrogenModTab()))
