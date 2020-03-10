package org.taymyr.lagom.demo.api

import io.swagger.v3.oas.annotations.media.Schema

import scala.beans.BeanProperty

@Schema(requiredProperties = Array("name", "detail"))
case class LagomError(@BeanProperty name: String, @BeanProperty detail: String)

object LagomError {
  import play.api.libs.json._
  implicit val format: Format[LagomError] = Json.format[LagomError]
}
