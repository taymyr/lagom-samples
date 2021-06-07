package org.taymyr.lagom.demo.impl

import com.lightbend.lagom.scaladsl.api.{Service, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import org.taymyr.lagom.demo.api.PetsService
import org.taymyr.lagom.scaladsl.openapi.OpenAPIRouter
import play.api.libs.ws.ahc.AhcWSComponents

class PetsLoader extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new PetsApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new PetsApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[PetsService])
}

abstract class PetsApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer = {
    val service = wire[PetsServiceImpl]
    serverFor[PetsService](service)
      .additionalRouter(wire[OpenAPIRouter].router(service))
  }

}
