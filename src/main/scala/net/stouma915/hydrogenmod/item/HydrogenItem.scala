package net.stouma915.hydrogenmod.item

import net.minecraft.world.effect.{MobEffectInstance, MobEffects}
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.{CreativeModeTab, Item}
import net.stouma915.hydrogenmod.HydrogenMod

object HydrogenItem {
  val item: Item = new Item(
    new Properties()
      .tab(CreativeModeTab.TAB_MISC)
      .food(
        new FoodProperties.Builder()
          .alwaysEat()
          .nutrition(256)
          .effect(
            () => new MobEffectInstance(MobEffects.REGENERATION, 1200),
            1.0f
          )
          .effect(
            () => new MobEffectInstance(MobEffects.LUCK, 1200),
            1.0f
          )
          .effect(
            () => new MobEffectInstance(MobEffects.SATURATION, 1200),
            1.0f
          )
          .build()
      )
  ).setRegistryName(HydrogenMod.MODID, "hydrogen")
}
