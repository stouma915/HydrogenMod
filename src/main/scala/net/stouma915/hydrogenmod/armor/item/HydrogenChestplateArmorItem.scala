package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object HydrogenChestplateArmorItem {

  private val instance: Item = new HydrogenChestplateArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_chestplate")

  def apply(): Item = instance

}

final class HydrogenChestplateArmorItem private ()
    extends HydrogenArmorItem(EquipmentSlot.CHEST)
