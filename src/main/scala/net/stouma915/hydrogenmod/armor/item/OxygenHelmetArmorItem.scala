package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object OxygenHelmetArmorItem {

  private val instance: Item = new OxygenHelmetArmorItem()
    .setRegistryName(HydrogenMod.ModId, "oxygen_helmet")

  def apply(): Item = instance

}

final class OxygenHelmetArmorItem private ()
    extends OxygenArmorItem(EquipmentSlot.HEAD)
