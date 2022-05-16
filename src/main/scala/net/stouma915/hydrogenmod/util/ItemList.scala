package net.stouma915.hydrogenmod.util

import net.minecraft.item.ItemStack

object ItemList {

  def of(items: List[ItemStack]): ItemList =
    new ItemList(items.map(_.copy))

}

class ItemList private (items: List[ItemStack]) {

  def head: ItemStack = items.head

  def last: ItemStack = items.last

  def headOption: Option[ItemStack] = items.headOption

  def lastOption: Option[ItemStack] = items.lastOption

  def length: Int = items.length

  def toList: List[ItemStack] = items

  def copied: ItemList = ItemList.of(items)

  def appended(suffix: ItemStack): ItemList =
    appendedAll(List(suffix))

  def appendedAll(suffix: ItemList): ItemList =
    appendedAll(suffix.toList)

  def appendedAll(suffix: List[ItemStack]): ItemList =
    ItemList.of(items.appendedAll(suffix))

  def appendedHead(prefix: ItemStack): ItemList =
    appendedHeadAll(List(prefix))

  def appendedHeadAll(prefix: ItemList): ItemList =
    appendedHeadAll(prefix.toList)

  def appendedHeadAll(prefix: List[ItemStack]): ItemList =
    ItemList.of(prefix).appendedAll(this)

  def get(index: Int): ItemStack = items(index)

  def getOrElse[A >: ItemStack](index: Int, default: Int => A): A =
    items.applyOrElse(index, default)

  def isEmpty: Boolean = length == 0 || items.forall(_.isEmpty)

  def nonEmpty: Boolean = !isEmpty

  def dropped(num: Int): ItemList = ItemList.of(items.drop(num))

  def droppedRight(num: Int): ItemList = ItemList.of(items.dropRight(num))

  def filter(predicate: ItemStack => Boolean): ItemList =
    ItemList.of(items.filter(predicate))

  def filterNot(predicate: ItemStack => Boolean): ItemList =
    ItemList.of(items.filterNot(predicate))

  def map(func: ItemStack => ItemStack): ItemList =
    ItemList.of(items.map(func))

  def contains(elem: ItemStack): Boolean =
    nonEmpty && items.contains(elem)

  def exists(predicate: ItemStack => Boolean): Boolean =
    nonEmpty && items.exists(predicate)

  def find(predicate: ItemStack => Boolean): Option[ItemStack] =
    items.find(predicate)

  def forall(predicate: ItemStack => Boolean): Boolean = items.forall(predicate)

  def foreach[U](func: ItemStack => U): Unit = items.foreach(func)

  def count(predicate: ItemStack => Boolean): Int = items.count(predicate)

  def updated(index: Int, another: ItemStack): ItemList =
    ItemList.of(items.updated(index, another))

}
