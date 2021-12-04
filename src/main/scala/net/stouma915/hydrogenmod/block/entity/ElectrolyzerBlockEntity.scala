package net.stouma915.hydrogenmod.block.entity

import io.netty.buffer.Unpooled
import net.minecraft.core.{BlockPos, Direction, NonNullList}
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.{Component, TextComponent}
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket
import net.minecraft.network.{Connection, FriendlyByteBuf}
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.entity.{
  BlockEntityType,
  RandomizableContainerBlockEntity
}
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.{ContainerHelper, WorldlyContainer}
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.wrapper.SidedInvWrapper
import net.minecraftforge.items.{
  CapabilityItemHandler,
  IItemHandler,
  IItemHandlerModifiable
}
import net.stouma915.hydrogenmod.HydrogenMod
import net.stouma915.hydrogenmod.block.ElectrolyzerBlock
import net.stouma915.hydrogenmod.gui.menu.ElectrolyzerMenu
import net.stouma915.hydrogenmod.implicits.*

import scala.jdk.CollectionConverters.*

object ElectrolyzerBlockEntity {

  private val instance: BlockEntityType[_] =
    BlockEntityType.Builder
      .of(
        new BlockEntityType.BlockEntitySupplier[ElectrolyzerBlockEntity] {
          override def create(p_155268_ : BlockPos, p_155269_ : BlockState)
              : ElectrolyzerBlockEntity =
            new ElectrolyzerBlockEntity(p_155268_, p_155269_)
        },
        ElectrolyzerBlock()
      )
      .build(null)
      .setRegistryName(HydrogenMod.ModId, "electrolyzer")

  def apply(): BlockEntityType[_] = instance

  private[block] def newInstance(
      blockPos: BlockPos,
      blockState: BlockState
  ): ElectrolyzerBlockEntity = new ElectrolyzerBlockEntity(blockPos, blockState)

}

sealed class ElectrolyzerBlockEntity private (
    blockPos: BlockPos,
    blockState: BlockState
) extends RandomizableContainerBlockEntity(
      ElectrolyzerBlockEntity(),
      blockPos,
      blockState
    )
    with WorldlyContainer {

  private var itemStacks = NonNullList.withSize[ItemStack](19, ItemStack.EMPTY)
  private val handlers: Array[LazyOptional[IItemHandlerModifiable]] =
    SidedInvWrapper.create(this, Direction.values: _*)

  override def load(p_155080_ : CompoundTag): Unit = {
    super.load(p_155080_)
    itemStacks = NonNullList.withSize(getContainerSize, ItemStack.EMPTY)

    if (!tryLoadLootTable(p_155080_) && p_155080_.contains("Items"))
      ContainerHelper.loadAllItems(p_155080_, itemStacks)
  }

  override def saveAdditional(p_187461_ : CompoundTag): Unit = {
    super.saveAdditional(p_187461_)
    if (!trySaveLootTable(p_187461_))
      ContainerHelper.saveAllItems(p_187461_, itemStacks, false)
  }

  override def isEmpty: Boolean =
    !itemStacks.asScala.toList.map(_.isEmpty).contains(false)

  override def getMaxStackSize: Int = 64

  override def getItems: NonNullList[ItemStack] = itemStacks

  override def setItems(p_59625_ : NonNullList[ItemStack]): Unit = itemStacks =
    p_59625_

  override def canPlaceItem(p_18952_ : Int, p_18953_ : ItemStack): Boolean =
    true

  override def getSlotsForFace(p_19238_ : Direction): Array[Int] =
    (0 to getContainerSize).toArray

  override def canPlaceItemThroughFace(
      p_19235_ : Int,
      p_19236_ : ItemStack,
      p_19237_ : Direction
  ): Boolean = canPlaceItem(p_19235_, p_19236_)

  override def canTakeItemThroughFace(
      p_19239_ : Int,
      p_19240_ : ItemStack,
      p_19241_ : Direction
  ): Boolean = true

  override def getCapability[T](
      cap: Capability[T],
      side: Direction
  ): LazyOptional[T] =
    if (
      !remove && side.nonNull && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
    )
      handlers(side.ordinal).cast
    else
      super.getCapability(cap, side)

  override def setRemoved(): Unit = {
    super.setRemoved()
    handlers.foreach(_.invalidate())
  }

  override def createMenu(
      p_58627_ : Int,
      p_58628_ : Inventory
  ): AbstractContainerMenu =
    ElectrolyzerMenu.newInstance(
      p_58627_,
      p_58628_,
      new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition)
    )

  override def getDefaultName: Component = new TextComponent("electrolyzer")

  override def getContainerSize: Int = 19

}
