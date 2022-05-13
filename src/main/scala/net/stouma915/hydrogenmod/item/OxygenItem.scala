package net.stouma915.hydrogenmod.item

import net.minecraft.item.Item

object OxygenItem {

  private val instance = new OxygenItem

  def apply(): Item = instance

}

final class OxygenItem private () extends Item(new Item.Properties())
