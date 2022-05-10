package net.stouma915.hydrogenmod.item

import net.minecraft.item.Item
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object HydrogenPeroxideItem {

  private val instance = new HydrogenPeroxideItem

  def apply(): Item = instance

}

final class HydrogenPeroxideItem private ()
    extends Item(new Item.Properties().tab(HydrogenModTab()))
