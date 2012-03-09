package config

import org.springframework.context.annotation.{Configuration, ComponentScan}

@Configuration
@ComponentScan(Array("org.nate.service",
                     "org.nate.springapp",
                     "org.nate.validator"))
class AppConfig {

//  lazy val logger: Logger = LoggerFactory.getLogger(this.getClass())
//  @Bean
//  def infoLog: Function1[]
}