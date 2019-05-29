package org.taymyr.lagom.demo.api

import io.swagger.v3.oas.annotations.media.Schema

import scala.beans.BeanProperty

@Schema(requiredProperties = Array("code", "message"))
case class Error(@BeanProperty code: Int, @BeanProperty message: String)

object Error {
  import play.api.libs.json._
  implicit val format: Format[Error] = Json.format[Error]
}
