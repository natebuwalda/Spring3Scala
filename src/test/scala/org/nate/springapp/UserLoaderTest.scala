package org.nate.springapp

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.nate.validator.RecordValidator
import org.mockito.Mockito._
import org.nate.service.UserService
import org.nate.domain.User

class UserLoaderTest extends FlatSpec with ShouldMatchers with MockitoSugar {

  private val validator = mock[RecordValidator]
  private val userService = mock[UserService]
  private val stringLoader = new StringUserLoader {
    override val recordValidator = validator 
  }
  private val fileLoader = new FileUserLoader {
    override val recordValidator = validator
  }

  "UserLoader with a single record" should "pass a valid record as a string to the UserService" in {
    val testString: String = "nate,buwalda,33"
    val expectedUser: User = User("nate", "buwalda", 33)
    when(validator.validate(testString)) thenReturn(None)
    when(userService.create(testString)) thenReturn(expectedUser)

    val result = stringLoader.load(testString)
    result should be ("%s was added".format(expectedUser))
  }
  
  it should "show an error if no record was provided" in (pending)

  it should "show an error if the record is invalid" in (pending)
  
  "UserLoader with a csv file" should "parse the lines into a collection to pass to the UserService" in (pending)
  
  it should "show an error if the file cannot be found" in (pending)
  
}