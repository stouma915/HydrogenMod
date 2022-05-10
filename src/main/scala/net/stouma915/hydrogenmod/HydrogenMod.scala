package net.stouma915.hydrogenmod

import cats.effect.IO
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.stouma915.hydrogenmod.block._
import net.stouma915.hydrogenmod.init.HydrogenModRegistries
import net.stouma915.hydrogenmod.item._
import net.stouma915.hydrogenmod.item.block._

object HydrogenMod {

  final val ModId = "hydrogenmod"

  final lazy val HydrogenItems = Set(
    HydrogenItem()
  )
  final lazy val OxygenItems = Set(
    OxygenItem()
  )

  private val eventBus = FMLJavaModLoadingContext.get.getModEventBus

  // region startup tasks

  private val registerItems = IO {
    Map(
      "hydrogen" -> HydrogenItem(),
      "oxygen" -> OxygenItem(),
      "hydrogen_peroxide" -> HydrogenPeroxideItem(),
      "electrolyzer" -> ElectrolyzerBlockItem()
    ).foreach {
      case (name: String, item: Item) =>
        HydrogenModRegistries.ItemRegistry.register(name, () => item)
    }
  }

  private val registerBlocks = IO {
    Map(
      "electrolyzer" -> ElectrolyzerBlock()
    ).foreach {
      case (name: String, block: Block) =>
        HydrogenModRegistries.BlockRegistry.register(name, () => block)
    }
  }

  private val registerEventBus = IO {
    HydrogenModRegistries.getAllRegistries.foreach(_.register(eventBus))
  }

  private val startHydrogenMod = for {
    _ <- registerItems
    _ <- registerBlocks
    _ <- registerEventBus
  } yield ()

  // endregion

}

@Mod(HydrogenMod.ModId)
final class HydrogenMod {

  import cats.effect.unsafe.implicits.global

  HydrogenMod.startHydrogenMod.unsafeRunSync()

}
