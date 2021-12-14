package net.stouma915.hydrogenmod.block

import io.netty.buffer.Unpooled
import net.minecraft.core.{BlockPos, NonNullList}
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.{Component, TranslatableComponent}
import net.minecraft.server.level.{ServerLevel, ServerPlayer}
import net.minecraft.world.entity.player.{Inventory, Player}
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.{ItemStack, Items}
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour.Properties
import net.minecraft.world.level.block.state.properties.{
  IntegerProperty,
  Property
}
import net.minecraft.world.level.block.state.{BlockState, StateDefinition}
import net.minecraft.world.level.block.{Block, EntityBlock, SoundType}
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.{BlockGetter, Level}
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.{
  BooleanOp,
  CollisionContext,
  Shapes,
  VoxelShape
}
import net.minecraft.world.{
  Containers,
  InteractionHand,
  InteractionResult,
  MenuProvider
}
import net.minecraftforge.network.NetworkHooks
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.block.entity.ElectrolyzerBlockEntity
import net.stouma915.hydrogenmod.gui.menu.ElectrolyzerMenu
import net.stouma915.hydrogenmod.implicits.*
import net.stouma915.hydrogenmod.inventory.ListInventory
import net.stouma915.hydrogenmod.item.BatteryItem
import net.stouma915.hydrogenmod.recipe.electrolysis.ElectrolysisRecipeRegistry

import java.util.Random
import scala.jdk.CollectionConverters.*
import scala.util.control.Breaks.*

object ElectrolyzerBlock {

  final val WaterLevelProperty =
    IntegerProperty.create("water_level", 0, 9)
  final val ProgressProperty =
    IntegerProperty.create("progress", 0, 6)

  private val instance: Block =
    new ElectrolyzerBlock().setRegistryName(HydrogenMod.ModId, "electrolyzer")

  def apply(): Block = instance

}

sealed class ElectrolyzerBlock private ()
    extends Block(
      Properties
        .of(Material.STONE)
        .sound(SoundType.GLASS)
        .noOcclusion()
        .destroyTime(0.45f)
        .requiresCorrectToolForDrops()
        .lightLevel(_ => 1)
    )
    with EntityBlock {

  registerDefaultState(
    stateDefinition
      .any()
      .setValue(ElectrolyzerBlock.WaterLevelProperty, 0)
      .setValue(ElectrolyzerBlock.ProgressProperty, 0)
  )

  override def createBlockStateDefinition(
      p_49915_ : StateDefinition.Builder[Block, BlockState]
  ): Unit = p_49915_
    .add(ElectrolyzerBlock.WaterLevelProperty)
    .add(ElectrolyzerBlock.ProgressProperty)

  override def newBlockEntity(
      p_153215_ : BlockPos,
      p_153216_ : BlockState
  ): BlockEntity = new ElectrolyzerBlockEntity(p_153215_, p_153216_)

  override def onPlace(
      p_60566_ : BlockState,
      p_60567_ : Level,
      p_60568_ : BlockPos,
      p_60569_ : BlockState,
      p_60570_ : Boolean
  ): Unit =
    p_60567_.scheduleTick(p_60568_, this, 0)

  override def tick(
      p_60462_ : BlockState,
      p_60463_ : ServerLevel,
      p_60464_ : BlockPos,
      p_60465_ : Random
  ): Unit = {
    val blockEntity = p_60463_.getBlockEntity(p_60464_) match {
      case entity: ElectrolyzerBlockEntity =>
        entity
      case _ =>
        throw new IllegalStateException(
          "Found something that was not electrolyzer."
        )
    }
    val listInventory =
      ListInventory.of(
        19,
        blockEntity.getItems.asScala.toList
      )
    val itemList = listInventory.asList
    val waterLevel = itemList.dropRight(10).count { itemStack =>
      ElectrolysisRecipeRegistry.getAll.exists(_.isCorrectAsInput(itemStack))
    }

    val isBatterySet = itemList(9).getItem == BatteryItem()
    val isInputCorrect = itemList
      .dropRight(10)
      .filterNot(elem => elem.isEmpty)
      .filterNot(elem => elem.getItem == Items.BUCKET)
      .forall(elem =>
        ElectrolysisRecipeRegistry.getAll.exists(_.isCorrectAsInput(elem))
      )
    val isSameItemInput = {
      val droppedItems = itemList
        .dropRight(10)
        .filterNot(elem => elem.isEmpty)
        .filterNot(elem => elem.getItem == Items.BUCKET)

      droppedItems.count(elem =>
        elem.sameItem(droppedItems.head)
      ) == droppedItems.length && droppedItems.nonEmpty
    }

    if (isBatterySet && isInputCorrect && isSameItemInput) {
      val inputItem = itemList
        .filterNot(elem => elem.isEmpty)
        .filterNot(elem => elem.getItem == Items.BUCKET)
        .head
      val electrolysisRecipe = ElectrolysisRecipeRegistry.getAll
        .find(elem => elem.isCorrectAsInput(inputItem))
        .getOrElse(
          throw new IllegalStateException(
            "The electrolysis recipe couldn't be identified."
          )
        )

      if (
        ListInventory
          .of(9, itemList.drop(10))
          .canAddItems(
            electrolysisRecipe.getOutputItems(inputItem)
          )
      ) {
        val currentProgress = getProgress(p_60463_, p_60464_)

        if (currentProgress >= 6) {
          var newInventory = listInventory.copied

          val damagedBattery = newInventory.asList(9).copy()
          damagedBattery.addDamage()
          newInventory = newInventory.updated(9, damagedBattery)

          updateState(p_60462_, p_60463_, p_60464_, 0, waterLevel)

          breakable {
            newInventory.asList.dropRight(10).zipWithIndex.foreach {
              case (itemStack: ItemStack, index: Int)
                  if !itemStack.isEmpty && itemStack.getItem != Items.BUCKET =>
                val bucketItemStack = Items.BUCKET.toGeneralItemStack
                newInventory = newInventory.updated(index, bucketItemStack)
                break()
              case _ =>
            }
          }

          val newOutputInventory = ListInventory
            .of(9, newInventory.asList.drop(10).map(_.copy()))
            .addedAll(electrolysisRecipe.getOutputItems(inputItem))

          newInventory = ListInventory.of(
            19,
            newInventory.asList
              .dropRight(9)
              .appendedAll(newOutputInventory.asList)
          )

          blockEntity.setItems(NonNullList.of(null, newInventory.toArray: _*))
        } else
          updateState(
            p_60462_,
            p_60463_,
            p_60464_,
            currentProgress + 1,
            waterLevel
          )
      } else updateState(p_60462_, p_60463_, p_60464_, 0, waterLevel)
    } else updateState(p_60462_, p_60463_, p_60464_, 0, waterLevel)

    p_60463_.scheduleTick(p_60464_, this, 5)
  }

  private def getProgress(
      level: Level,
      blockPos: BlockPos
  ): Int =
    level
      .getBlockEntity(blockPos)
      .getBlockState
      .getValue(
        ElectrolyzerBlock.ProgressProperty.asInstanceOf[Property[Nothing]]
      )

  private def updateState(
      blockState: BlockState,
      level: Level,
      blockPos: BlockPos,
      newProgress: Int,
      newWaterLevel: Int
  ): Unit = {
    val newBlockState =
      blockState
        .setValue(ElectrolyzerBlock.ProgressProperty, newProgress)
        .setValue(ElectrolyzerBlock.WaterLevelProperty, newWaterLevel)

    level.setBlock(blockPos, newBlockState, 3)
  }

  override def getShape(
      p_51973_ : BlockState,
      p_51974_ : BlockGetter,
      p_51975_ : BlockPos,
      p_51976_ : CollisionContext
  ): VoxelShape = {

    import scala.language.implicitConversions

    implicit def convertTupleToBox(
        tuple: (Int, Int, Int, Int, Int, Int)
    ): VoxelShape = Block.box(
      tuple._1,
      tuple._2,
      tuple._3,
      tuple._4,
      tuple._5,
      tuple._6
    )

    Shapes.join(
      Shapes.or(
        (3, 30, 7, 5, 32, 9),
        (3, 30, 7, 5, 32, 9),
        (0, 0, 0, 16, 32, 16)
      ),
      Shapes.or(
        (0, 0, 1, 16, 9, 15),
        (0, 10, 0, 16, 32, 4),
        (0, 10, 12, 16, 32, 16),
        (0, 10, 0, 1, 32, 16),
        (15, 10, 0, 16, 32, 16),
        (0, 30, 0, 16, 32, 16)
      ),
      BooleanOp.ONLY_FIRST
    )
  }

  override def use(
      p_60503_ : BlockState,
      p_60504_ : Level,
      p_60505_ : BlockPos,
      p_60506_ : Player,
      p_60507_ : InteractionHand,
      p_60508_ : BlockHitResult
  ): InteractionResult = {
    p_60506_ match {
      case serverPlayer: ServerPlayer =>
        val extraData =
          new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(p_60505_)

        NetworkHooks.openGui(
          serverPlayer,
          new MenuProvider {
            override def getDisplayName: Component =
              new TranslatableComponent("gui.hydrogenmod.electrolyzer.title")

            override def createMenu(
                p_39954_ : Int,
                p_39955_ : Inventory,
                p_39956_ : Player
            ): AbstractContainerMenu = new ElectrolyzerMenu(
              p_39954_,
              p_39955_,
              extraData
            )
          },
          p_60505_
        )
      case _ =>
    }

    InteractionResult.SUCCESS
  }

  override def getMenuProvider(
      p_60563_ : BlockState,
      p_60564_ : Level,
      p_60565_ : BlockPos
  ): MenuProvider = {
    val blockEntity = p_60564_.getBlockEntity(p_60565_)
    blockEntity match {
      case menuProvider: MenuProvider =>
        menuProvider
      case _ =>
        null
    }
  }

  override def onRemove(
      p_60515_ : BlockState,
      p_60516_ : Level,
      p_60517_ : BlockPos,
      p_60518_ : BlockState,
      p_60519_ : Boolean
  ): Unit = {
    if (p_60515_.getBlock != p_60518_.getBlock) {
      val blockEntity = p_60516_.getBlockEntity(p_60517_)
      blockEntity match {
        case entity: ElectrolyzerBlockEntity =>
          Containers.dropContents(p_60516_, p_60517_, entity)
          p_60516_.updateNeighbourForOutputSignal(p_60517_, this)
        case _ =>
      }
    }
  }

}
