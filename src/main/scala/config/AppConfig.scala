package config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ComponentScan

@Configuration
@ComponentScan(Array("org.nate.service",
                     "org.nate.springapp",
                     "org.nate.validator"))
class AppConfig 