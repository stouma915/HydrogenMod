package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.OxygenArmorMaterial

object OxygenHelmetItem {

  private val instance = new OxygenHelmetItem

  def apply(): Item = instance

}

final class OxygenHelmetItem private ()
    extends ArmorItem(
      OxygenArmorMaterial(),
      EquipmentSlotType.HEAD,
      new Item.Properties()
    )
