package net.stouma915.hydrogenmod.recipe.electrolysis

import cats.effect.IO
import net.minecraft.item.{ItemStack, Items}
import net.stouma915.hydrogenmod.implicits._
import net.stouma915.hydrogenmod.item.{HydrogenItem, OxygenItem}

object ElectrolysisOfWater {

  private val instance = new ElectrolysisOfWater

  def apply(): ElectrolysisRecipe = instance

}

final class ElectrolysisOfWater private () extends ElectrolysisRecipe {

  import cats.effect.unsafe.implicits.global

  override def isInputCorrect(inputItem: ItemStack): IO[Boolean] =
    IO {
      inputItem.getItem eq Items.WATER_BUCKET
    }

  override def outputItems(inputItem: ItemStack): IO[List[ItemStack]] =
    IO {
      // to avoid unused warnings
      if (isInputCorrect(inputItem).unsafeRunSync())
        List(
          HydrogenItem()
            .makeStack
            .setAmount(2),
          OxygenItem()
            .makeStack
            .setAmount(1)
        )
      else
        List()
    }

  override def itemToLeave(inputItem: ItemStack): IO[Option[ItemStack]] =
    IO {
      if (inputItem.getItem eq Items.WATER_BUCKET)
        Some(Items.BUCKET.makeStack)
      else
        None
    }

}
