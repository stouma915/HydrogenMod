package net.stouma915.hydrogenmod.recipe.electrolysis

import cats.effect.IO
import net.minecraft.item.ItemStack

trait ElectrolysisRecipe {

  /***
    * Returns true if `inputItem` is correct as an input item.
    * Example: Water bucket
    *
    * @param inputItem input item
    * @return correct or not
    */
  def isInputCorrect(inputItem: ItemStack): IO[Boolean]

  /***
    * Items output as a result of electrolysis.
    * Example: 2x Hydrogen and 1x Oxygen
    *
    * @param inputItem input item
    * @return output items
    */
  def outputItems(inputItem: ItemStack): IO[List[ItemStack]]

  /***
    * The item to be left in Input Slot after electrolysis is completed.
    * Example: Empty bucket
    *
    * @param inputItem input item
    * @return item to leave
    */
  def itemToLeave(inputItem: ItemStack): IO[Option[ItemStack]] = None

}
