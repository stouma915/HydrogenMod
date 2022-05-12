package net.stouma915.hydrogenmod.item.block

import net.minecraft.item.{BlockItem, Item}
import net.stouma915.hydrogenmod.block.ElectrolyzerBlock
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object ElectrolyzerBlockItem {

  private val instance = new ElectrolyzerBlockItem

  def apply(): Item = instance

}

final class ElectrolyzerBlockItem private ()
    extends BlockItem(
      ElectrolyzerBlock(),
      new Item.Properties()
        .tab(HydrogenModTab())
    )
