package net.stouma915.hydrogenmod.implicits

trait NullCheck {

  implicit class AnyRefOps(anyRef: AnyRef) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

}
