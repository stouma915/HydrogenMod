package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.HydrogenArmorMaterial

object HydrogenHelmetItem {

  private val instance = new HydrogenHelmetItem

  def apply(): Item = instance

}

final class HydrogenHelmetItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlotType.HEAD,
      new Item.Properties()
    )
