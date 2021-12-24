package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.{ArmorItem, Item}
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.material.HydrogenArmorMaterial
import net.stouma915.hydrogenmod.creativetab.HydrogenModTab

object HydrogenLeggingsArmorItem {

  private val instance: Item = new HydrogenLeggingsArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_leggings")

  def apply(): Item = instance

}

final class HydrogenLeggingsArmorItem private ()
    extends ArmorItem(
      HydrogenArmorMaterial(),
      EquipmentSlot.LEGS,
      new Properties().tab(HydrogenModTab())
    )
