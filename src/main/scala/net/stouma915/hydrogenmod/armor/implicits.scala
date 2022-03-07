package net.stouma915.hydrogenmod.armor

import net.minecraft.world.item.Item.Properties
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object implicits {

  implicit val defaultProperties: Properties =
    new Properties().tab(HydrogenModTab())

}
