package org.nate.service

import org.springframework.stereotype.Component
import org.nate.domain.User

trait UserService {
  def create(userInfo: String): User

}

@Component
class BaseUserService extends UserService {
  def create(userInfo: String) = {
    val fields = userInfo.split(",")
    User(fields(0),fields(1),fields(2).toInt)
  }
}