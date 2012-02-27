package org.nate.validator

import org.springframework.stereotype.Component
import collection.{Seq, mutable}
import java.lang.String
import org.nate.domain.{UserRecord, User}

trait RecordValidator {
  def validate(record: String): Option[List[String]]
}

@Component
class UserRecordValidator extends RecordValidator {
  
  def validate(record: String): Option[List[String]] = {
    val errors = mutable.ListBuffer[String]()
    
    record match {
      case null => errors += "Record cannot be empty"
      case rec if rec.isEmpty => errors += "Record cannot be empty"
      case rec => rec match {
          case UserRecord(first, last, age) => {
            if (first.isEmpty) errors += "Must include a first name"
            if (last.isEmpty) errors += "Must include a last name"
            if (age.isEmpty) errors += "Must include an age"

            try {
              age.toInt
            } catch {
              case _: NumberFormatException => errors += "Age must be an integer"
            }
          }
          case _ => errors += "Record has an invalid number of fields"
        }
    }

    if (errors.isEmpty) None else Some(errors.toList)
  }

}