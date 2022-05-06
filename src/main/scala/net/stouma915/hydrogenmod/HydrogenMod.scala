package net.stouma915.hydrogenmod

import cats.effect.IO
import net.minecraft.item.Item
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.stouma915.hydrogenmod.init.HydrogenModRegistry
import net.stouma915.hydrogenmod.item._

object HydrogenMod {

  final val ModId = "hydrogenmod"

}

@Mod(HydrogenMod.ModId)
final class HydrogenMod {

  import cats.effect.unsafe.implicits.global

  private val eventBus = FMLJavaModLoadingContext.get.getModEventBus

  // region startup tasks

  private val registerItems = IO {
    Map(
      "hydrogen" -> HydrogenItem(),
      "oxygen" -> OxygenItem()
    ).foreach {
      case (itemId: String, item: Item) =>
        HydrogenModRegistry.ItemRegistry.register(itemId, () => item)
    }
  }

  private val registerEventBus = IO {
    HydrogenModRegistry.getAllRegistries.foreach(_.register(eventBus))
  }

  private val startHydrogenMod = for {
    _ <- registerItems
    _ <- registerEventBus
  } yield ()

  // endregion

  startHydrogenMod.unsafeRunSync()

}
