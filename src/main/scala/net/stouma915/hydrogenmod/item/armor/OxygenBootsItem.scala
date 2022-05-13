package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.OxygenArmorMaterial

object OxygenBootsItem {

  private val instance = new OxygenBootsItem

  def apply(): Item = instance

}

final class OxygenBootsItem private ()
    extends ArmorItem(
      OxygenArmorMaterial(),
      EquipmentSlotType.FEET,
      new Item.Properties()
    )
