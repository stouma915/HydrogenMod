package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.armor.implicits.defaultProperties

object HydrogenBootsArmorItem {

  private val instance: Item = new HydrogenBootsArmorItem()
    .setRegistryName(HydrogenMod.ModId, "hydrogen_boots")

  def apply(): Item = instance

}

final class HydrogenBootsArmorItem private ()
    extends HydrogenArmorItem(EquipmentSlot.FEET)
