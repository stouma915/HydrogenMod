package net.stouma915.hydrogenmod.material.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.IArmorMaterial
import net.minecraft.item.crafting.Ingredient
import net.minecraft.util.{SoundEvent, SoundEvents}
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.item.HydrogenItem

object HydrogenArmorMaterial {

  private val instance = new HydrogenArmorMaterial

  def apply(): IArmorMaterial = instance

}

final class HydrogenArmorMaterial private () extends IArmorMaterial {

  override def getDurabilityForSlot(p_200896_1_ : EquipmentSlotType): Int =
    Int.MaxValue

  override def getDefenseForSlot(p_200902_1_ : EquipmentSlotType): Int = 0

  override def getEnchantmentValue: Int = 0

  override def getEquipSound: SoundEvent = SoundEvents.ARMOR_EQUIP_LEATHER

  override def getRepairIngredient: Ingredient = Ingredient.of(HydrogenItem())

  override def getName: String = s"${HydrogenMod.ModId}:hydrogen"

  override def getToughness: Float = 0.0f

  override def getKnockbackResistance: Float = 0.0f

}
