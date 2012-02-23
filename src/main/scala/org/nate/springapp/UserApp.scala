package org.nate.springapp

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.nate.Logging

object SpringApp extends App with Logging {
  logger.info("Starting User Load Batch Application")
  
  private val appCtx = new AnnotationConfigApplicationContext
  appCtx.scan("config")
  appCtx.refresh
	
  val userLoader: UserLoader = appCtx.getBean("stringUserLoader", classOf[UserLoader])
  val result = userLoader.load("bob,evans,40")
  logger.info(result)
  
  logger.info("Spring User Load Batch Application done")
}
