package net.stouma915.hydrogenmod.gui.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory
import net.stouma915.hydrogenmod.gui.menu.ElectrolyzerMenu

sealed class ElectrolyzerScreen private[gui] (
    container: ElectrolyzerMenu,
    inventory: Inventory,
    text: Component
) extends AbstractContainerScreen[ElectrolyzerMenu](
      container,
      inventory,
      text
    ) {

  override def render(
      poseStack: PoseStack,
      mouseX: Int,
      mouseY: Int,
      partialTicks: Float
  ): Unit = {
    renderBackground(poseStack)
    super.render(poseStack, mouseX, mouseY, partialTicks)
    renderTooltip(poseStack, mouseX, mouseY)
  }

  override def containerTick(): Unit =
    super.containerTick()

  override def onClose(): Unit = {
    super.onClose()
    Minecraft.getInstance.keyboardHandler.setSendRepeatsToGui(false)
  }

  override def init(): Unit = {
    super.init()
    minecraft.keyboardHandler.setSendRepeatsToGui(true)
  }

  override protected def renderBg(
      poseStack: PoseStack,
      partialTicks: Float,
      gx: Int,
      gy: Int
  ): Unit = {
    RenderSystem.setShader(() => GameRenderer.getPositionTexShader)
    RenderSystem.setShaderColor(1, 1, 1, 1)
    RenderSystem.setShaderTexture(
      0,
      new ResourceLocation(
        "hydrogenmod:textures/gui/electrolyzer_menu.png"
      )
    )

    val x = (width - imageWidth) / 2
    val y = (height - imageHeight) / 2

    blit(
      poseStack,
      x,
      y,
      0,
      0,
      imageWidth,
      imageHeight
    )

    val progress = menu.getProgress
    if (progress != 0)
      blit(
        poseStack,
        x + 76,
        y + 44,
        176,
        0,
        progress * 4,
        16
      )
  }

}
