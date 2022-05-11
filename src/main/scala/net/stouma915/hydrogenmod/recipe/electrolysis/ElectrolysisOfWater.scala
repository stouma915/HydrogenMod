package net.stouma915.hydrogenmod.recipe.electrolysis

import net.minecraft.item.{ItemStack, Items}
import net.stouma915.hydrogenmod.implicits._
import net.stouma915.hydrogenmod.item.{HydrogenItem, OxygenItem}

object ElectrolysisOfWater {

  private val instance = new ElectrolysisOfWater

  def apply(): ElectrolysisRecipe = instance

}

final class ElectrolysisOfWater private () extends ElectrolysisRecipe {

  override def isInputCorrect(inputItem: ItemStack): Boolean =
    inputItem.getItem eq Items.WATER_BUCKET

  override def outputItems(_ : ItemStack): List[ItemStack] =
    List(
      {
        val itemStack = HydrogenItem().toGeneralItemStack
        itemStack.setCount(2)
        itemStack
      },
      OxygenItem().toGeneralItemStack
    )

  override def itemToLeave(inputItem: ItemStack): Option[ItemStack] =
    if (inputItem eq Items.WATER_BUCKET)
      Some(Items.BUCKET.toGeneralItemStack)
    else
      None

}
