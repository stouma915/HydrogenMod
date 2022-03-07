package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object HydrogenHelmetArmorItem {

  private val instance: Item = new HydrogenHelmetArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_helmet")

  def apply(): Item = instance

}

final class HydrogenHelmetArmorItem private ()
    extends HydrogenArmorItem(EquipmentSlot.HEAD)
