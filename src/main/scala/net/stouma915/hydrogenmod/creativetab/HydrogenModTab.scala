package net.stouma915.hydrogenmod.creativetab

import net.minecraft.item.{ItemGroup, ItemStack}
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.implicits._
import net.stouma915.hydrogenmod.item.HydrogenItem

object HydrogenModTab {

  private val instance = new HydrogenModTab

  def apply(): ItemGroup = instance

}

final class HydrogenModTab private () extends ItemGroup(HydrogenMod.ModId) {

  override def makeIcon(): ItemStack = HydrogenItem().toGeneralItemStack

}
