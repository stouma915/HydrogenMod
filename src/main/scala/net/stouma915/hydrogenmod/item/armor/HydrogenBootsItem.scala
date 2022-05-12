package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab
import net.stouma915.hydrogenmod.material.armor.HydrogenArmorMaterial

object HydrogenBootsItem {

  private val instance = new HydrogenBootsItem

  def apply(): Item = instance

}

final class HydrogenBootsItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlotType.FEET,
      new Item.Properties().tab(HydrogenModTab())
    )
