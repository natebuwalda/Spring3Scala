import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}
appender("FILE", RollingFileAppender) {
  file = "c://Temp//spring3app.log"
  rollingPolicy(FixedWindowRollingPolicy) {
    fileNamePattern = "spring3app.%i.log.bak"
    minIndex = 1
    maxIndex = 5
  }
  triggeringPolicy(SizeBasedTriggeringPolicy) {
    maxFileSize = "5MB"
  }
  encoder(PatternLayoutEncoder) {
    pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}
logger("org.nate", DEBUG, ["STDOUT", "FILE"], false)
root(INFO, ["STDOUT", "FILE"])