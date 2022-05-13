package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.OxygenArmorMaterial

object OxygenLeggingsItem {

  private val instance = new OxygenLeggingsItem

  def apply(): Item = instance

}

final class OxygenLeggingsItem private ()
    extends ArmorItem(
      OxygenArmorMaterial(),
      EquipmentSlotType.LEGS,
      new Item.Properties()
    )
