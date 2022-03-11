package net.stouma915.hydrogenmod.syntax

import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.{Item, ItemStack}
import net.stouma915.hydrogenmod.HydrogenMod

trait ItemImplicits {

  implicit class ItemOps(item: Item) {

    def canDestroy: Boolean = item.isDamageable(toGeneralItemStack)

    def isHydrogenItem: Boolean = HydrogenMod.HydrogenItems.contains(item)

    def toGeneralItemStack: ItemStack = new ItemStack(item)

  }

  implicit class ItemStackOps(itemStack: ItemStack) {

    def toGeneralItemStack: ItemStack = itemStack.getItem.toGeneralItemStack

    def destroyItem(livingEntity: LivingEntity): Unit =
      itemStack.hurtAndBreak(
        itemStack.getMaxDamage,
        livingEntity,
        _ => {}
      )

    def addDamage(): Unit =
      if (itemStack.getDamageValue >= itemStack.getMaxDamage - 1)
        itemStack.setCount(itemStack.getCount - 1)
      else
        itemStack.setDamageValue(itemStack.getDamageValue + 1)

  }

}
