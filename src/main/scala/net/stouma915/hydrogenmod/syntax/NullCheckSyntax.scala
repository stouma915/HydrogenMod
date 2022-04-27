package net.stouma915.hydrogenmod.syntax

trait NullCheckSyntax {

  implicit class AnyRefOps[A <: AnyRef](anyRef: A) {

    def isNull: Boolean = anyRef == null

    def nonNull: Boolean = !isNull

    def ifNull(alternative: => A): A =
      if (anyRef.isNull) alternative else anyRef

  }

}
