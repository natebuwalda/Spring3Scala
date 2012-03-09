package config

import org.springframework.context.annotation.{Bean, Configuration, ComponentScan}
import org.slf4j.{LoggerFactory, Logger}


@Configuration
@ComponentScan(Array("org.nate.service",
                     "org.nate.springapp",
                     "org.nate.validator"))
class AppConfig {

  @Bean
  def infoLog: (Class[_], String) => Unit = {
    (clazz: Class[_], message: String) => {
      val logger: Logger = LoggerFactory.getLogger(clazz)
      logger.info(message)
    }
  }

  @Bean
  def errorLog: (Class[_], String) => Unit = {
    (clazz: Class[_], message: String) => {
      val logger: Logger = LoggerFactory.getLogger(clazz)
      logger.error(message)
    }
  }

  @Bean
  def debugLog: (Class[_], String) => Unit = {
    (clazz: Class[_], message: String) => {
      val logger: Logger = LoggerFactory.getLogger(clazz)
      logger.error(message)
    }
  }


}

