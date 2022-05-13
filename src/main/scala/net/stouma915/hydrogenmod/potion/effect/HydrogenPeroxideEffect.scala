package net.stouma915.hydrogenmod.potion.effect

import net.minecraft.entity.{Entity, LivingEntity}
import net.minecraft.potion.{Effect, EffectType, InstantEffect}

object HydrogenPeroxideEffect {

  private val instance = new HydrogenPeroxideEffect

  def apply(): Effect = instance

}

final class HydrogenPeroxideEffect private ()
    extends InstantEffect(EffectType.BENEFICIAL, 3035801) {

  override def applyInstantenousEffect(
      p_180793_1_ : Entity,
      p_180793_2_ : Entity,
      p_180793_3_ : LivingEntity,
      p_180793_4_ : Int,
      p_180793_5_ : Double
  ): Unit = p_180793_3_.heal(1.0f)

}
