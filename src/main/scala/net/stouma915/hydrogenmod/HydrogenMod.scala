package net.stouma915.hydrogenmod

import cats.effect.IO
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.potion.Potion
import net.minecraftforge.common.brewing.BrewingRecipeRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.stouma915.hydrogenmod.block._
import net.stouma915.hydrogenmod.init.{
  ElectrolysisRecipeRegistry,
  HydrogenModRegistries
}
import net.stouma915.hydrogenmod.item._
import net.stouma915.hydrogenmod.item.armor._
import net.stouma915.hydrogenmod.item.block._
import net.stouma915.hydrogenmod.item.tool._
import net.stouma915.hydrogenmod.potion._
import net.stouma915.hydrogenmod.recipe.brewing._
import net.stouma915.hydrogenmod.recipe.electrolysis._

object HydrogenMod {

  import cats.effect.unsafe.implicits.global

  final val ModId = "hydrogenmod"

  final lazy val HydrogenItems = Set(
    HydrogenItem(),
    HydrogenHelmetItem(),
    HydrogenChestplateItem(),
    HydrogenLeggingsItem(),
    HydrogenBootsItem(),
    HydrogenSwordItem(),
    HydrogenShovelItem(),
    HydrogenPickaxeItem(),
    HydrogenAxeItem(),
    HydrogenHoeItem()
  )
  final lazy val OxygenItems = Set(
    OxygenItem(),
    OxygenHelmetItem(),
    OxygenChestplateItem(),
    OxygenLeggingsItem(),
    OxygenBootsItem(),
    OxygenSwordItem(),
    OxygenShovelItem(),
    OxygenPickaxeItem(),
    OxygenAxeItem(),
    OxygenHoeItem()
  )

  private val eventBus = FMLJavaModLoadingContext.get.getModEventBus

  // region startup tasks

  private val registerItems = IO {
    Map(
      "hydrogen" -> HydrogenItem(),
      "oxygen" -> OxygenItem(),
      "hydrogen_peroxide" -> HydrogenPeroxideItem(),
      "electrolyzer" -> ElectrolyzerBlockItem(),
      "hydrogen_helmet" -> HydrogenHelmetItem(),
      "hydrogen_chestplate" -> HydrogenChestplateItem(),
      "hydrogen_leggings" -> HydrogenLeggingsItem(),
      "hydrogen_boots" -> HydrogenBootsItem(),
      "oxygen_helmet" -> OxygenHelmetItem(),
      "oxygen_chestplate" -> OxygenChestplateItem(),
      "oxygen_leggings" -> OxygenLeggingsItem(),
      "oxygen_boots" -> OxygenBootsItem(),
      "hydrogen_sword" -> HydrogenSwordItem(),
      "hydrogen_shovel" -> HydrogenShovelItem(),
      "hydrogen_pickaxe" -> HydrogenPickaxeItem(),
      "hydrogen_axe" -> HydrogenAxeItem(),
      "hydrogen_hoe" -> HydrogenHoeItem(),
      "oxygen_sword" -> OxygenSwordItem(),
      "oxygen_shovel" -> OxygenShovelItem(),
      "oxygen_pickaxe" -> OxygenPickaxeItem(),
      "oxygen_axe" -> OxygenAxeItem(),
      "oxygen_hoe" -> OxygenHoeItem()
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

  private val registerPotions = IO {
    Map(
      "hydrogen_water" -> HydrogenWaterPotion(),
      "oxygen_water" -> OxygenWaterPotion()
    ).foreach {
      case (name: String, potion: Potion) =>
        HydrogenModRegistries.PotionRegistry.register(name, () => potion)
    }
  }

  private val registerBrewingRecipes = IO {
    Set(
      HydrogenWaterRecipe(),
      OxygenWaterRecipe()
    ).foreach(BrewingRecipeRegistry.addRecipe)
  }

  private val registerElectrolysisRecipes = IO {
    Set(
      ElectrolysisOfWater()
    ).foreach(ElectrolysisRecipeRegistry.register(_).unsafeRunSync())
  }

  private val registerEventBus = IO {
    HydrogenModRegistries.getAllRegistries.foreach(_.register(eventBus))
  }

  private val startHydrogenMod = for {
    _ <- registerItems
    _ <- registerBlocks
    _ <- registerPotions
    _ <- registerBrewingRecipes
    _ <- registerElectrolysisRecipes
    _ <- registerEventBus
  } yield ()

  // endregion

}

@Mod(HydrogenMod.ModId)
final class HydrogenMod {

  import cats.effect.unsafe.implicits.global

  HydrogenMod.startHydrogenMod.unsafeRunSync()

}
