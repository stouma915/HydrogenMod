package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.material.HydrogenArmorMaterial
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object HydrogenChestplateArmorItem {

  private val instance: Item = new HydrogenChestplateArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_chestplate")

  def apply(): Item = instance

}

final class HydrogenChestplateArmorItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlot.CHEST,
      new Properties().tab(HydrogenModTab())
    )
