package net.stouma915.hydrogenmod.potion

import net.minecraft.potion.Potion

object OxygenWaterPotion {

  private val instance = new OxygenWaterPotion

  def apply(): Potion = instance

}

final class OxygenWaterPotion private () extends Potion
