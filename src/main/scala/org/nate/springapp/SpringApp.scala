package org.nate.springapp

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.ApplicationContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object SpringApp extends App {
	
	val logger: Logger = LoggerFactory.getLogger(this.getClass())
	
	logger.info("Starting Spring 3 Application")
	
	val appCtx = new AnnotationConfigApplicationContext()
	appCtx.scan("config")
	appCtx.refresh()
	
	logger.info("Spring 3 Application done")
}