package net.stouma915.hydrogenmod.potion

import net.minecraft.potion.Potion

object HydrogenWaterPotion {

  private val instance = new HydrogenWaterPotion

  def apply(): Potion = instance

}

final class HydrogenWaterPotion private () extends Potion
