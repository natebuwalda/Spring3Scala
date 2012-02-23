package org.nate.springapp

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.ApplicationContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.nate.Logging

object SpringApp extends App with Logging {
  logger.info("Starting User Load Batch Application")
  
  private val appCtx = new AnnotationConfigApplicationContext
  appCtx.scan("config")
  appCtx.refresh
	
  logger.info("Spring User Load Batch Application done")
}
