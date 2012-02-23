package org.nate.springapp

import org.scalatest.FlatSpec
import org.scalatest.BeforeAndAfter
import org.scalatest.matchers.ShouldMatchers

class UserLoaderTest extends FlatSpec with BeforeAndAfter with ShouldMatchers {
	
  "UserLoader with a single record" should "pass the record as a string to the UserService" in (pending)
  
  it should "show an error if no record was provided" in (pending)
  
  "UserLoader with a csv file" should "parse the lines into a collection to pass to the UserService" in (pending)
  
  it should "show an error if the file cannot be found" in (pending)
  
}