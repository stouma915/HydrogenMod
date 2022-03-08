package net.stouma915.hydrogenmod.implicits

trait NullChecks {

  implicit class AnyRefOps(anyRef: AnyRef) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

  implicit class AnyRefListOps[A <: AnyRef](anyRefList: List[A]) {

    def hasNull: Boolean = anyRefList.exists(_.isNull)

  }

}
