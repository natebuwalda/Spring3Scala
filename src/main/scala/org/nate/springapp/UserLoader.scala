package org.nate.springapp

import org.springframework.stereotype.Component

trait UserLoader {
  def load(loadInfo: String): String
}

@Component
class StringUserLoader extends UserLoader {
  def load(loadInfo: String): String = null
}

@Component
class FileUserLoader extends UserLoader {
  def load(loadInfo: String): String = null
}