package net.stouma915.hydrogenmod.syntax

trait NullCheckSyntax {

  implicit class AnyRefOps(anyRef: AnyRef) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

}
