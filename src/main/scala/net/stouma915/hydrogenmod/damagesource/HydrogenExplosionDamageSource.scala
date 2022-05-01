package net.stouma915.hydrogenmod.damagesource

import net.minecraft.util.DamageSource
import net.stouma915.hydrogenmod.HydrogenMod

object HydrogenExplosionDamageSource {

  private val instance = new HydrogenExplosionDamageSource

  def apply(): DamageSource = instance

}

final class HydrogenExplosionDamageSource private ()
    extends DamageSource(s"${HydrogenMod.ModId}_hydrogen_explosion") {

  override def isExplosion: Boolean = true

}
