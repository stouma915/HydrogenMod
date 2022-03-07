package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object OxygenLeggingsArmorItem {

  private val instance: Item = new OxygenLeggingsArmorItem()
    .setRegistryName(HydrogenMod.ModId, "oxygen_leggings")

  def apply(): Item = instance

}

final class OxygenLeggingsArmorItem private ()
    extends OxygenArmorItem(EquipmentSlot.LEGS)
