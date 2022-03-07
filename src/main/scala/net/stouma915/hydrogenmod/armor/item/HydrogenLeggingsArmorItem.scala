package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object HydrogenLeggingsArmorItem {

  private val instance: Item = new HydrogenLeggingsArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_leggings")

  def apply(): Item = instance

}

final class HydrogenLeggingsArmorItem private ()
    extends HydrogenArmorItem(EquipmentSlot.LEGS)
