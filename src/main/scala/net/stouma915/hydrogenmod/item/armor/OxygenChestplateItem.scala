package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.OxygenArmorMaterial

object OxygenChestplateItem {

  private val instance = new OxygenChestplateItem

  def apply(): Item = instance

}

final class OxygenChestplateItem private ()
    extends ArmorItem(
      OxygenArmorMaterial(),
      EquipmentSlotType.CHEST,
      new Item.Properties()
    )
