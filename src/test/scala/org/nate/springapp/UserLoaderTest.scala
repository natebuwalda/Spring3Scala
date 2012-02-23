package org.nate.springapp

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class UserLoaderTest extends FlatSpec with ShouldMatchers {
	
  "UserLoader with a single record" should "pass a valid record as a string to the UserService" in {
    val loader = new StringUserLoader
    val result = loader.load("nate,buwalda,33")
    result should be ("User(Nate Buwalda, 33) was added")
  }
  
  it should "show an error if no record was provided" in (pending)

  it should "show an error if the record is invalid" in (pending)
  
  "UserLoader with a csv file" should "parse the lines into a collection to pass to the UserService" in (pending)
  
  it should "show an error if the file cannot be found" in (pending)
  
}