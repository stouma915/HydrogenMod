package net.stouma915.hydrogenmod.recipe.brewing

import net.minecraft.item.{ItemStack, Items}
import net.minecraft.potion.{PotionUtils, Potions}
import net.minecraftforge.common.brewing.IBrewingRecipe
import net.stouma915.hydrogenmod.implicits._
import net.stouma915.hydrogenmod.item.HydrogenItem
import net.stouma915.hydrogenmod.potion.HydrogenWaterPotion

object HydrogenWaterRecipe {

  private val instance = new HydrogenWaterRecipe

  def apply(): IBrewingRecipe = instance

}

final class HydrogenWaterRecipe private () extends IBrewingRecipe {

  override def isInput(input: ItemStack): Boolean = {
    val inputItem = input.getItem
    (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION) && PotionUtils
      .getPotion(input) == Potions.WATER
  }

  override def isIngredient(ingredient: ItemStack): Boolean =
    ingredient.getItem eq HydrogenItem()

  override def getOutput(input: ItemStack, ingredient: ItemStack): ItemStack =
    if (isInput(input) && isIngredient(ingredient))
      PotionUtils.setPotion(
        input.makeStack,
        HydrogenWaterPotion()
      )
    else
      ItemStack.EMPTY

}
