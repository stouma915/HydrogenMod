package net.stouma915.hydrogenmod.creativetab

import net.minecraft.item.{ItemGroup, ItemStack}
import net.minecraft.util.NonNullList
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.implicits._
import net.stouma915.hydrogenmod.item._
import net.stouma915.hydrogenmod.item.armor._
import net.stouma915.hydrogenmod.item.block._
import net.stouma915.hydrogenmod.item.tool._

object HydrogenModTab {

  final lazy val Items = List(
    HydrogenItem(),
    OxygenItem(),
    HydrogenPeroxideItem(),
    ElectrolyzerBlockItem(),
    HydrogenHelmetItem(),
    HydrogenChestplateItem(),
    HydrogenLeggingsItem(),
    HydrogenBootsItem(),
    OxygenHelmetItem(),
    OxygenChestplateItem(),
    OxygenLeggingsItem(),
    OxygenBootsItem(),
    HydrogenSwordItem(),
    HydrogenShovelItem(),
    HydrogenPickaxeItem(),
    HydrogenAxeItem(),
    HydrogenHoeItem(),
    OxygenSwordItem(),
    OxygenShovelItem(),
    OxygenPickaxeItem(),
    OxygenAxeItem(),
    OxygenHoeItem()
  )

  private val instance = new HydrogenModTab

  def apply(): ItemGroup = instance

}

final class HydrogenModTab private () extends ItemGroup(HydrogenMod.ModId) {

  override def makeIcon(): ItemStack = HydrogenItem().toGeneralItemStack

  override def fillItemList(p_78018_1_ : NonNullList[ItemStack]): Unit = {
    p_78018_1_.clear()

    HydrogenModTab.Items.map(_.toGeneralItemStack).foreach(p_78018_1_.add)
  }

}
