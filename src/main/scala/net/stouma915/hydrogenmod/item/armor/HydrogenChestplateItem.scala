package net.stouma915.hydrogenmod.item.armor

import net.minecraft.inventory.EquipmentSlotType
import net.minecraft.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.material.armor.HydrogenArmorMaterial

object HydrogenChestplateItem {

  private val instance = new HydrogenChestplateItem

  def apply(): Item = instance

}

final class HydrogenChestplateItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlotType.CHEST,
      new Item.Properties()
    )
