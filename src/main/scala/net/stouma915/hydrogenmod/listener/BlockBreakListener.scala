package net.stouma915.hydrogenmod.listener

import net.minecraft.world.level.block.FireBlock
import net.minecraftforge.event.world.BlockEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.stouma915.hydrogenmod.armor.item.{
  HydrogenBootsArmorItem,
  HydrogenChestplateArmorItem,
  HydrogenHelmetArmorItem,
  HydrogenLeggingsArmorItem
}
import net.stouma915.hydrogenmod.implicits.*
import net.stouma915.hydrogenmod.item.{BatteryItem, HydrogenItem}
import net.stouma915.hydrogenmod.tool.item.*
import net.stouma915.hydrogenmod.util.Util

class BlockBreakListener {

  @SubscribeEvent
  def onBlockBreak(event: BlockEvent.BreakEvent): Unit =
    event.getState.getBlock match {
      case _: FireBlock =>
        val itemInMainHand =
          event.getPlayer.getInventory.getSelected

        if (itemInMainHand.getItem.isHydrogenItem) {
          if (!event.getPlayer.isCreative && !event.getPlayer.isSpectator) {
            if (itemInMainHand.getItem.canDestroy)
              itemInMainHand.destroyItem(event.getPlayer)
            else
              itemInMainHand.setCount(itemInMainHand.getCount - 1)
          }
          Util.performHydrogenExplosion(
            event.getPlayer.level,
            event.getPlayer.getPos
          )
        }

        if (itemInMainHand.getItem == BatteryItem()) {
          if (!event.getPlayer.isCreative && !event.getPlayer.isSpectator)
            itemInMainHand.destroyItem(event.getPlayer)
          Util.performBatteryExplosion(
            event.getPlayer.level,
            event.getPlayer.getPos
          )
        }
      case _ =>
    }

}
