package org.nate.springapp

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.mock.MockitoSugar
import org.nate.validator.RecordValidator
import org.mockito.Mockito._
import org.nate.service.UserService
import org.nate.domain.User
import java.nio.file.Paths
import java.lang.String
import org.mockito.Matchers
import org.scalatest.{BeforeAndAfter, FlatSpec}

class UserLoaderTest extends FlatSpec with ShouldMatchers with BeforeAndAfter with MockitoSugar {

  private val mockValidator = mock[RecordValidator]
  private val mockUserService = mock[UserService]

  private val stringLoader = new StringUserLoader {
    override val recordValidator = mockValidator
    override val userService = mockUserService
  }

  private val fileLoader = new FileUserLoader {
    override val recordValidator = mockValidator
    override val userService = mockUserService
  }

  before {
    reset(mockValidator, mockUserService)
  }

  "UserLoader with a single record" should "pass a valid record as a string to the UserService" in {
    val testString: String = "nate,buwalda,33"
    val expectedUser: User = User("nate", "buwalda", 33)
    when(mockValidator.validate(testString)) thenReturn(None)
    when(mockUserService.create(testString)) thenReturn(expectedUser)

    val result = stringLoader.load(testString)
    result should be ("%s was added".format(expectedUser))
  }

  "UserLoader with a csv file" should "parse the lines into a collection to pass to the UserService" in {
    val testFilePath: String = "src/test/resources/testfile.csv"
    
    println(Paths.get(testFilePath).toAbsolutePath)
    
    when(mockValidator.validate("nate,buwalda,33")) thenReturn(None)
    when(mockValidator.validate("bob,evans,40")) thenReturn(None)
    when(mockValidator.validate("nick,buwalda,30")) thenReturn(None)
    when(mockUserService.create("nate,buwalda,33")) thenReturn(User("nate", "buwalda", 33))
    when(mockUserService.create("bob,evans,40")) thenReturn(User("bob", "evans", 40))
    when(mockUserService.create("nick,buwalda,30")) thenReturn(User("nick", "buwalda", 30))
    
    val result = fileLoader.load(testFilePath)
    result should be ("3 users added successfully")
    
    verify(mockValidator, times(3)).validate(Matchers.anyString)
    verify(mockUserService, times(3)).create(Matchers.anyString)
  }
  
  it should "show an error if the file cannot be found" in (pending)
  
}