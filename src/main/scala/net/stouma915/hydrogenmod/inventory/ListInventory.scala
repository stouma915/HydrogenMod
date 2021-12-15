package net.stouma915.hydrogenmod.inventory

import net.minecraft.world.item.{ItemStack, Items}
import net.stouma915.hydrogenmod.implicits.*

object ListInventory {

  def create(size: Int): ListInventory = {
    if (size <= 0)
      throw new IllegalArgumentException("The size must be 1 or more.")

    ListInventory.of(
      (1 to size).map(_ => Items.AIR.toGeneralItemStack).toList
    )
  }

  def of(items: List[ItemStack]): ListInventory = {
    val itemsToSet =
      if (items.isEmpty)
        List(Items.AIR.toGeneralItemStack)
      else
        items.map(_.copy())

    new ListInventory(itemsToSet)
  }

}

class ListInventory private (items: List[ItemStack]) {

  def head: ItemStack = items.head

  def last: ItemStack = items.last

  def length: Int = items.length

  def get(index: Int): ItemStack = items(index)

  def getOrElse[A >: ItemStack](index: Int, default: Int => A): A =
    items.applyOrElse(index, default)

  def filter(predicate: ItemStack => Boolean): ListInventory =
    ListInventory.of(items.filter(predicate))

  def filterNot(predicate: ItemStack => Boolean): ListInventory =
    ListInventory.of(items.filterNot(predicate))

  def map[B](func: ItemStack => B): List[B] =
    items.map(func)

  def contains(elem: ItemStack): Boolean =
    nonEmpty && items.exists(_.sameItem(elem))

  def nonEmpty: Boolean = !isEmpty

  def isEmpty: Boolean = items.forall(_.isEmpty)

  def exists(predicate: ItemStack => Boolean): Boolean =
    nonEmpty && items.exists(predicate)

  def find(predicate: ItemStack => Boolean): Option[ItemStack] =
    items.find(predicate)

  def forall(predicate: ItemStack => Boolean): Boolean = items.forall(predicate)

  def foreach[U](func: ItemStack => U): Unit = items.foreach(func)

  def copied: ListInventory = ListInventory.of(items.map(_.copy()))

  def count(predicate: ItemStack => Boolean): Int = items.count(predicate)

  def updated(index: Int, newItemStack: ItemStack): ListInventory =
    ListInventory.of(items.updated(index, newItemStack))

  def appended(suffix: ListInventory): ListInventory =
    ListInventory.of(items.appendedAll(suffix.asList))

  def added(itemStack: ItemStack): ListInventory =
    addedAll(List(itemStack))

  def addedAll(itemStacks: List[ItemStack]): ListInventory = {
    if (itemStacks.isEmpty)
      return this

    val unstackableItemsToPlace = itemStacks.filterNot(_.isStackable)
    val stackableItemsToPlace = itemStacks.filter(_.isStackable)

    var overflowingItems = List[ItemStack]()

    def fillAirWithItems(
        items: List[ItemStack],
        current: List[ItemStack]
    ): List[ItemStack] = {
      var itemIndex = 0
      current.zipWithIndex
        .map { case (itemStack: ItemStack, index: Int) =>
          if (itemStack.isEmpty) {
            itemIndex += 1
            (
              items.applyOrElse(itemIndex - 1, _ => itemStack),
              index
            )
          } else (itemStack, index)
        }
        .map(_._1)
    }

    val result = for {
      afterAddUnstackable <- Some {
        fillAirWithItems(unstackableItemsToPlace, asList.map(_.copy()))
      }
      itemsStackedToMax <- Some {
        stackableItemsToPlace.filter(elem =>
          elem.getCount >= elem.getMaxStackSize
        )
      }
      afterAddStackedToMax <- Some {
        fillAirWithItems(itemsStackedToMax, afterAddUnstackable)
      }
      itemsNotIncluded <- Some {
        stackableItemsToPlace
          .filterNot(elem => elem.getCount >= elem.getMaxStackSize)
          .filterNot(elem =>
            afterAddStackedToMax.exists(item => item.sameItem(elem))
          )
      }
      afterAddNotIncluded <- Some {
        fillAirWithItems(itemsNotIncluded, afterAddStackedToMax)
      }
      itemsIncluded <- Some {
        stackableItemsToPlace
          .filterNot(elem => elem.getCount >= elem.getMaxStackSize)
          .filter(elem =>
            afterAddStackedToMax.exists(item => item.sameItem(elem))
          )
      }
      _ <- Some {
        itemsIncluded
          .foreach { itemStack =>
            val sameItems = afterAddNotIncluded
              .filter(item => item.sameItem(itemStack))
            val canBeStacked =
              sameItems.map(item => item.getMaxStackSize - item.getCount).sum

            // format: off
            if (itemStack.getCount == canBeStacked)
              sameItems.foreach(item => item.setCount(item.getMaxStackSize))
            else if (itemStack.getCount > canBeStacked) {
              sameItems.foreach(item => item.setCount(item.getMaxStackSize))
              val overflowingItem = itemStack
              overflowingItem.setCount(overflowingItem.getCount - canBeStacked)
              overflowingItems = overflowingItems.appended(overflowingItem)
            }
            else if (itemStack.getCount < canBeStacked)
              while (itemStack.getCount > 0)
                sameItems.foreach { item =>
                  item.setCount(item.getCount + 1)
                  itemStack.setCount(itemStack.getCount - 1)
                }
            // format: on
          }
      }
      afterAddOverflowingItems <- Some {
        fillAirWithItems(overflowingItems, afterAddNotIncluded)
      }
    } yield afterAddOverflowingItems

    ListInventory.of(
      result.getOrElse(
        throw new IllegalStateException(
          "This should never happen: Failed to unwrap Option."
        )
      )
    )
  }

  def asList: List[ItemStack] = items

  def dropped(num: Int): ListInventory =
    ListInventory.of(items.drop(num))

  def droppedRight(num: Int): ListInventory =
    ListInventory.of(items.dropRight(num))

  def canAddItem(itemToPlace: ItemStack): Boolean =
    canAddItems(List(itemToPlace))

  def canAddItems(itemsToPlace: List[ItemStack]): Boolean = {
    if (itemsToPlace.isEmpty || isEmpty)
      return true

    val numberOfEmptySlot =
      asList.count(_.isEmpty)
    val unstackableItems = asList.filterNot(_.isStackable)
    val unstackableItemsToPlace = itemsToPlace.filterNot(_.isStackable)
    val stackableItems = asList.filter(_.isStackable)
    val stackableItemsToPlace = itemsToPlace.filter(_.isStackable)
    
    // format: off
    if (itemsToPlace.length <= numberOfEmptySlot)
      return true
    if (unstackableItems.length >= items.length)
      return false
    if (unstackableItemsToPlace.length > numberOfEmptySlot)
      return false
    if (stackableItemsToPlace.isEmpty && unstackableItemsToPlace.length <= numberOfEmptySlot)
      return true
    if (unstackableItemsToPlace.isEmpty && !stackableItems.exists(elem => elem.getCount < elem.getMaxStackSize))
      return false
    // format: on

    if (unstackableItemsToPlace.isEmpty) {
      var count = 1
      val bool = stackableItemsToPlace.forall { elem =>
        val sameItems = stackableItems.filter(_.sameItem(elem))
        if (sameItems.isEmpty) {
          count += 1
          if (numberOfEmptySlot >= count - 1)
            true
          else
            false
        } else {
          if (
            sameItems
              .map(e => e.getMaxStackSize - e.getCount)
              .sum >= elem.getCount
          ) true
          else {
            count += 1
            if (numberOfEmptySlot >= count - 1)
              true
            else false
          }
        }
      }

      if (bool)
        return true
    }

    if (unstackableItemsToPlace.nonEmpty && stackableItemsToPlace.nonEmpty) {
      if (unstackableItemsToPlace.length >= numberOfEmptySlot)
        return false

      val numberOfEmptySlotAfterPlacingUnstackable =
        numberOfEmptySlot - unstackableItemsToPlace.length
      if (numberOfEmptySlotAfterPlacingUnstackable <= 0)
        return false

      var count = 1
      Option.when {
        stackableItemsToPlace.forall { elem =>
          val sameItems = stackableItemsToPlace.filter(_.sameItem(elem))
          if (sameItems.isEmpty) {
            count += 1
            if (numberOfEmptySlotAfterPlacingUnstackable >= count - 1)
              true
            else
              false
          } else {
            if (
              sameItems
                .map(e => e.getMaxStackSize - e.getCount)
                .sum >= elem.getCount
            ) true
            else {
              count += 1
              if (numberOfEmptySlot >= count - 1)
                true
              else false
            }
          }
        }
      } {
        return true
      }
    }

    false
  }

}
