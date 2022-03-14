package net.stouma915.hydrogenmod.syntax

trait NullCheckSyntax {

  implicit class AnyRefOps(anyRef: AnyRef) {

    def nonNull: Boolean = !isNull

    def isNull: Boolean = anyRef == null

  }

  implicit class AnyRefListOps[A <: AnyRef](anyRefList: List[A]) {

    def hasNull: Boolean = anyRefList.exists(_.isNull)

  }

  implicit class AnyRefSetOps[A <: AnyRef](anyRefSet: Set[A]) {

    def hasNull: Boolean = anyRefSet.exists(_.isNull)

  }

  implicit class AnyRefSeqOps[A <: AnyRef](anyRefSeq: Seq[A]) {

    def hasNull: Boolean = anyRefSeq.exists(_.isNull)

  }

}
