package net.stouma915.hydrogenmod.init

import net.minecraftforge.registries.{DeferredRegister, ForgeRegistries}
import net.stouma915.hydrogenmod.HydrogenMod

private[hydrogenmod] object HydrogenModRegistries {

  final val BlockRegistry =
    DeferredRegister.create(ForgeRegistries.BLOCKS, HydrogenMod.ModId)
  final val ItemRegistry =
    DeferredRegister.create(ForgeRegistries.ITEMS, HydrogenMod.ModId)
  final val PotionRegistry =
    DeferredRegister.create(ForgeRegistries.POTION_TYPES, HydrogenMod.ModId)
  final val PotionEffectRegistry =
    DeferredRegister.create(ForgeRegistries.POTIONS, HydrogenMod.ModId)

  def getAllRegistries: Set[DeferredRegister[_]] = Set(
    BlockRegistry,
    ItemRegistry,
    PotionRegistry,
    PotionEffectRegistry
  )

}
