package org.nate.springapp

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import org.nate.validator.RecordValidator
import org.nate.Logging
import org.nate.service.UserService
import java.nio.file.{Files, Paths}
import java.nio.charset.Charset
import scala.collection.JavaConverters._

trait UserLoader {
  self: CommonUserLoaderDependencies with Logging =>

  def load(loadInfo: String): String

  protected def handleRecord(record: String): String = {
    recordValidator.validate(record) match {
      case Some(errors) => {
        errors foreach { error => errorLog(error) }
        "Failed to load the user record (%s)".format(record)
      }
      case None => {
        val user = userService.create(record)
        "%s was added".format(user)
      }
    }
  }
}

trait CommonUserLoaderDependencies {
  
  @Autowired
  @Qualifier("userRecordValidator")
  val recordValidator: RecordValidator = null

  @Autowired
  @Qualifier("baseUserService")
  val userService: UserService = null
}

@Component
class StringUserLoader extends UserLoader with CommonUserLoaderDependencies with Logging {
  def load(loadInfo: String): String = handleRecord(loadInfo)
}

@Component
class FileUserLoader extends UserLoader with CommonUserLoaderDependencies with Logging {
  def load(loadInfo: String): String = {
    val userFile = Paths.get(loadInfo)
    val lines = Files.readAllLines(userFile, Charset.defaultCharset()).asScala
    lines foreach { line => {
        val recordResult: String = handleRecord(line)
        infoLog(recordResult)
      }
    }
    "%s users added successfully".format(lines.size)
  }
}