package net.stouma915.hydrogenmod.recipe.electrolysis

import net.minecraft.item.ItemStack

trait ElectrolysisRecipe {

  def isInputCorrect(itemStack: ItemStack): Boolean

  def outputItems: List[ItemStack]

  def itemToLeave: Option[ItemStack] = None

}
