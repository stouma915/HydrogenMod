package net.stouma915.hydrogenmod.armor.item

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.Item.Properties
import net.stouma915.hydrogenmod.armor.material.OxygenArmorMaterial

class OxygenArmorItem(equipmentSlot: EquipmentSlot)(implicit
    properties: Properties
) extends ArmorItem(
      OxygenArmorMaterial(),
      equipmentSlot,
      properties
    )
