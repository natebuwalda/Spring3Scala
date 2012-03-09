package org.nate.springapp

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.nate.Logging
import org.springframework.beans.factory.annotation.{Qualifier, Autowire}

object SpringApp extends App with Logging {
  
  logger.info("Starting User Load Batch Application")
  
  private val appCtx = new AnnotationConfigApplicationContext
  appCtx.scan("config")
  appCtx.refresh

  if (args.length != 2) throwArgumentError
  
	private val runType: String = args(0)
	private val command: String = args(1)

  lazy val userLoader: UserLoader = {
    runType match {
      case "single" => appCtx.getBean("stringUserLoader", classOf[UserLoader])
      case "file" => appCtx.getBean("fileUserLoader", classOf[UserLoader])
      case _ => throwArgumentError
    }
  }

  val result = userLoader.load(command)
  logger.info(result)

  logger.info("Spring User Load Batch Application done")
  
  def throwArgumentError = throw new IllegalArgumentException("Please specify single or file entry and the record")
}
