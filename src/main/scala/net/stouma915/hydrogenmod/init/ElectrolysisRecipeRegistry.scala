package net.stouma915.hydrogenmod.init

import cats.effect.IO
import net.stouma915.hydrogenmod.recipe.electrolysis.ElectrolysisRecipe

object ElectrolysisRecipeRegistry {

  private var recipes = Set[ElectrolysisRecipe]()

  def register(recipe: ElectrolysisRecipe): IO[Unit] = IO {
    recipes = recipes + recipe
  }

  def getAll: IO[Set[ElectrolysisRecipe]] = IO.pure(recipes)

}
