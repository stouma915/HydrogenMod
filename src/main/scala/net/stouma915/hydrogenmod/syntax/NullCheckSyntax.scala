package net.stouma915.hydrogenmod.syntax

trait NullCheckSyntax {

  implicit class AnyRefOps[A <: AnyRef](anyRef: A) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

}
