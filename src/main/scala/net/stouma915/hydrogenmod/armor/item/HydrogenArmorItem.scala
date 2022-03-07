package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item.Properties
import net.stouma915.hydrogenmod.armor.material.HydrogenArmorMaterial

class HydrogenArmorItem(equipmentSlot: EquipmentSlot)(implicit
    properties: Properties
) extends ArmorItem(
      HydrogenArmorMaterial(),
      equipmentSlot,
      properties
    )
