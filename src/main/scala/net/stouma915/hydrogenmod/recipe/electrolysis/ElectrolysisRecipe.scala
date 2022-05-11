package net.stouma915.hydrogenmod.recipe.electrolysis

import net.minecraft.item.ItemStack

trait ElectrolysisRecipe {

  /***
    * Returns true if `inputItem` is correct as an input item.
    * Example: Water bucket
    *
    * @param inputItem input item
    * @return correct or not
    */
  def isInputCorrect(inputItem: ItemStack): Boolean

  /***
    * Items output as a result of electrolysis.
    * Example: 2x Hydrogen and 1x Oxygen
    *
    * @return output items
    */
  def outputItems: List[ItemStack]

  /***
    * The item to be left in Input Slot after electrolysis is completed.
    * Example: Empty bucket
    *
    * @return item to leave
    */
  def itemToLeave: Option[ItemStack] = None

}
