package net.stouma915.hydrogenmod.potion.effect

import net.minecraft.world.effect.MobEffect
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.stouma915.hydrogenmod.HydrogenMod

@Mod.EventBusSubscriber(
  modid = HydrogenMod.MODID,
  bus = Mod.EventBusSubscriber.Bus.MOD
)
object PotionEffectRegister {
  @SubscribeEvent
  def registerEffect(event: RegistryEvent.Register[MobEffect]): Unit = {
    event.getRegistry.register(HydrogenWaterEffect.effect)
    event.getRegistry.register(OxygenWaterEffect.effect)
  }
}