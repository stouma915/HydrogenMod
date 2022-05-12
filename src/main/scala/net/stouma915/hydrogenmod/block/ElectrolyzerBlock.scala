package net.stouma915.hydrogenmod.block

import net.minecraft.block.{AbstractBlock, Block}
import net.minecraft.block.material.{Material, MaterialColor}

object ElectrolyzerBlock {

  private val instance = new ElectrolyzerBlock

  def apply(): Block = instance

}

final class ElectrolyzerBlock private ()
    extends Block(
      AbstractBlock.Properties
        .of(Material.STONE, MaterialColor.STONE)
    )
