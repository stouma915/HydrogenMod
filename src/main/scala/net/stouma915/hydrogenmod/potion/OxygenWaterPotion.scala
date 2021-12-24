package net.stouma915.hydrogenmod.potion

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.potion.effect.OxygenWaterEffect

object OxygenWaterPotion {

  private val instance: Potion =
    new OxygenWaterPotion().setRegistryName(HydrogenMod.ModId, "oxygen_water")

  def apply(): Potion = instance

}

final class OxygenWaterPotion private ()
    extends Potion(
      new MobEffectInstance(OxygenWaterEffect(), 1200, 0, false, true)
    )
