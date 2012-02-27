package org.nate.domain

object User {
  def apply(userRecord: String): User = {
    val fields = userRecord.split(",")
    new User(fields(0),fields(1),fields(2).toInt)
  }
}

case class User(firstName: String, lastName: String, age: Int)

object UserRecord {
  def unapply(userRecord: String): Option[(String,  String, String)] = {
    userRecord match {
      case null => None
      case record: String if record.isEmpty => None
      case record: String => {
        val fields = record.split(",")
        if (fields.size == 3) Some(fields(0),fields(1),fields(2)) else None
      }
      case _ => null
    }
  }
}