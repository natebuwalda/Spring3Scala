package org.nate.springapp

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.nate.Logging
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

object UserApp extends App {

  private val appCtx = new AnnotationConfigApplicationContext
  appCtx.scan("config")
  appCtx.refresh()

  val app: LoadRunner = appCtx.getBean("loadRunner", classOf[LoadRunner])
  app.run(args)
}

@Component
class LoadRunner extends Runner with Logging {

  @Autowired
  private val appCtx: AnnotationConfigApplicationContext = null

  def run(args: Array[String]) {

    infoLog("Starting User Load Batch Application")

    if (args.length != 2) throwArgumentError

    val runType: String = args(0)
    val command: String = args(1)
    val userLoader: UserLoader = {
      runType match {
        case "single" => appCtx.getBean("stringUserLoader", classOf[UserLoader])
        case "file" => appCtx.getBean("fileUserLoader", classOf[UserLoader])
        case _ => throwArgumentError
      }
    }

    val result = userLoader.load(command)
    infoLog(result)
    infoLog("Spring User Load Batch Application done")
  }

  private def throwArgumentError = throw new IllegalArgumentException("Please specify single or file entry and the record")
}

trait Runner {
  self: Logging =>

  def run(args: Array[String]): Unit
}
