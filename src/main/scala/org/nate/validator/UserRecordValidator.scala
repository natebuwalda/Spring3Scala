package org.nate.validator

import org.springframework.stereotype.Component
import collection.{Seq, mutable}
import java.lang.String

trait RecordValidator {
  def validate(record: String): Option[List[String]]
}

@Component
class UserRecordValidator extends RecordValidator {
  
  def validate(record: String): Option[List[String]] = {
    val errors = mutable.ListBuffer[String]()
    
    val fields: Seq[String] = record.split(",").toList
    if (fields.size != 3) errors += "Record has an invalid number of fields" else fields match {
      case first :: last :: age :: Nil => {
        if (first.isEmpty) errors += "Must include a first name"
        if (last.isEmpty) errors += "Must include a last name"
        if (age.isEmpty) errors += "Must include an age"

        try {
          age.toInt
        } catch {
          case _: NumberFormatException => errors += "Age must be an integer"
        }
      }
      case _ => "Invalid record format"
    }

    if (errors.isEmpty) None else Some(errors.toList)
  }

}