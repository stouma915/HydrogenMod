package net.stouma915.hydrogenmod.block

import net.minecraft.block.{AbstractBlock, Block}
import net.minecraft.block.material.{Material, MaterialColor}
import net.minecraftforge.common.ToolType

object ElectrolyzerBlock {

  private val instance = new ElectrolyzerBlock

  def apply(): Block = instance

}

final class ElectrolyzerBlock private ()
    extends Block(
      AbstractBlock.Properties
        .of(Material.STONE, MaterialColor.STONE)
        .requiresCorrectToolForDrops()
        .harvestTool(ToolType.PICKAXE)
        .strength(1f, 10f)
    )
