package net.stouma915.hydrogenmod.material.tool

import net.minecraft.item.IItemTier
import net.minecraft.item.crafting.Ingredient
import net.stouma915.hydrogenmod.item.OxygenItem

object OxygenTier {

  private val instance = new OxygenTier

  def apply(): IItemTier = instance

}

final class OxygenTier private () extends IItemTier {

  override def getUses: Int = 50

  override def getSpeed: Float = 0.0f

  override def getAttackDamageBonus: Float = 0.0f

  override def getLevel: Int = 0

  override def getEnchantmentValue: Int = 0

  override def getRepairIngredient: Ingredient = Ingredient.of(OxygenItem())

}
