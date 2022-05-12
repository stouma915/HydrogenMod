package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab
import net.stouma915.hydrogenmod.material.armor.HydrogenArmorMaterial

object HydrogenLeggingsItem {

  private val instance = new HydrogenLeggingsItem

  def apply(): Item = instance

}

final class HydrogenLeggingsItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlotType.LEGS,
      new Item.Properties().tab(HydrogenModTab())
    )
