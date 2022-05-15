package net.stouma915.hydrogenmod.util

import net.minecraft.item.ItemStack

object ItemList {

  def of(items: List[ItemStack]): ItemList =
    new ItemList(items)

}

class ItemList private (items: List[ItemStack]) {

  def head: ItemStack = items.head

  def last: ItemStack = items.last

  def headOption: Option[ItemStack] = items.headOption

  def lastOption: Option[ItemStack] = items.lastOption

  def length: Int = items.length

  def toList: List[ItemStack] = items

  def appended(suffix: List[ItemStack]): ItemList =
    ItemList.of(items.appendedAll(suffix))

  def appended(suffix: ItemList): ItemList =
    appended(suffix.toList)

}
