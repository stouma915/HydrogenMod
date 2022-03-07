package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object OxygenChestplateArmorItem {

  private val instance: Item = new OxygenChestplateArmorItem()
    .setRegistryName(HydrogenMod.ModId, "oxygen_chestplate")

  def apply(): Item = instance

}

final class OxygenChestplateArmorItem private ()
    extends OxygenArmorItem(EquipmentSlot.CHEST)
