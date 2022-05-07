package net.stouma915.hydrogenmod.init

import net.minecraftforge.registries.{DeferredRegister, ForgeRegistries}
import net.stouma915.hydrogenmod.HydrogenMod

private[hydrogenmod] object HydrogenModRegistries {

  final val ItemRegistry =
    DeferredRegister.create(ForgeRegistries.ITEMS, HydrogenMod.ModId)

  def getAllRegistries: Set[DeferredRegister[_]] =
    Set(ItemRegistry)

}
