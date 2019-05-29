package org.taymyr.lagom.demo.impl

import java.io.IOException

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.google.common.base.Charsets
import com.google.common.io.Resources
import com.lightbend.lagom.scaladsl.server.LocalServiceLocator
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import com.lightbend.lagom.scaladsl.testkit.ServiceTest.TestServer
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import play.libs.Json

class PetsServiceSpec
    extends AsyncWordSpec
    with Matchers
    with BeforeAndAfterAll {

  lazy val server: TestServer[PetsApplication] =
    ServiceTest.startServer(ServiceTest.defaultSetup) { ctx =>
      new PetsApplication(ctx) with LocalServiceLocator
    }

  def resourceAsString(resourceName: String): String =
    try Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8)
    catch {
      case _: IOException => null
    }

  def yamlToJson(yaml: String): String = {
    try {
      val obj = new ObjectMapper(new YAMLFactory).readValue(yaml, classOf[Any])
      return new ObjectMapper().writeValueAsString(obj)
    } catch {
      case e: IOException => e.printStackTrace()
    }
    null
  }

  "Pets service" should {
    "return correct OpenAPI specification" in {
      server.application.wsClient
        .url(
          s"http://localhost:${server.playServer.httpPort.get}/_pets/openapi"
        )
        .get
        .map { response =>
          val expected = Json.parse(yamlToJson(resourceAsString("pets.yml")))
          response.status should ===(200)
          response.contentType should ===("application/x-yaml")
          Json.parse(yamlToJson(response.body)) should ===(expected)
        }
    }
  }

  protected override def beforeAll(): Unit = server

  protected override def afterAll(): Unit = server.stop()

}
