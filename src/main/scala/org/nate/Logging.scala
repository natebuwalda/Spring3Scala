package org.nate

import org.springframework.beans.factory.annotation.{Qualifier, Autowired}

trait Logging {
  @Autowired
  @Qualifier("infoLog")
  val info: (Class[_], String) => Unit = null

  @Autowired
  @Qualifier("errorLog")
  val error: (Class[_], String) => Unit = null

  @Autowired
  @Qualifier("debugLog")
  val debug: (Class[_], String) => Unit = null
  
  def infoLog(message: String) {
    info(this.getClass, message)
  }

  def errorLog(message: String) {
    error(this.getClass, message)
  }

  def debugLog(message: String) {
    debug(this.getClass, message)
  }
}