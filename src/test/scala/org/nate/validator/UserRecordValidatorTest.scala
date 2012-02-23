package org.nate.validator

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class UserRecordValidatorTest extends FlatSpec with ShouldMatchers {

  private val validator = new UserRecordValidator

  "Validation of a user record" should "be positive for a valid record" in {
    val errorMessages = validator.validate("nate,buwalda,33")
    errorMessages should be(None)
  }

  it should "be negative for a record without all three fields" in {
    val errorMessages = validator.validate("nate,33")
    errorMessages match {
      case None => fail("Excpected an error message")
      case Some(errors) => errors(0) should be("Record has an invalid number of fields")
    }
  }

  it should "be negative for a record with an invalid age" in {
    val errorMessages = validator.validate("nate,buwalda,aa")
    errorMessages match {
      case None => fail("Excpected an error message")
      case Some(errors) => errors(0) should be("Age must be an integer")
    }
  }

  it should "be negative for a record with an empty first name" in {
    val errorMessages = validator.validate(",buwalda,33")
    errorMessages match {
      case None => fail("Excpected an error message")
      case Some(errors) => errors(0) should be("Must include a first name")
    }
  }

  it should "be negative for a record with an empty last name" in {
    val errorMessages = validator.validate("nate,,33")
    errorMessages match {
      case None => fail("Excpected an error message")
      case Some(errors) => errors(0) should be("Must include a last name")
    }
  }

}