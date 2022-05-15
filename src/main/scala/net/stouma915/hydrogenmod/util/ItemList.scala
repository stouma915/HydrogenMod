package net.stouma915.hydrogenmod.util

import net.minecraft.item.ItemStack

object ItemList {

  def of(items: List[ItemStack]): ItemList =
    new ItemList(items)

}

class ItemList private (items: List[ItemStack]) {

  def appended(another: List[ItemStack]): ItemList =
    ItemList.of(items.appendedAll(another))

}
