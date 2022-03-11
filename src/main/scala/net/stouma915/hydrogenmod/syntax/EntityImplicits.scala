package net.stouma915.hydrogenmod.syntax

import net.minecraft.core.BlockPos
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.{Entity, LivingEntity}

trait EntityImplicits {

  implicit class EntityOps(entity: Entity) {

    def getPos: BlockPos = new BlockPos(entity.getX, entity.getY, entity.getZ)

  }

  implicit class LivingEntityOps(livingEntity: LivingEntity) {

    def killWithCause(cause: DamageSource): Unit =
      livingEntity.hurt(cause, livingEntity.getMaxHealth)

  }

}
