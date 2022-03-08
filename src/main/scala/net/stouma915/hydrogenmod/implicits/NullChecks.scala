package net.stouma915.hydrogenmod.implicits

trait NullChecks {

  implicit class AnyRefOps(anyRef: AnyRef) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

}
