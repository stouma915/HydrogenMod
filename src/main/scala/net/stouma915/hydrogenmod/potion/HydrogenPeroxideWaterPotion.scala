package net.stouma915.hydrogenmod.potion

import net.minecraft.potion.{EffectInstance, Potion}
import net.stouma915.hydrogenmod.potion.effect.HydrogenPeroxideEffect

object HydrogenPeroxideWaterPotion {

  private val instance = new HydrogenPeroxideWaterPotion

  def apply(): Potion = instance

}

final class HydrogenPeroxideWaterPotion private ()
    extends Potion(new EffectInstance(HydrogenPeroxideEffect()))
