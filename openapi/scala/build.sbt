organization in ThisBuild := "org.taymyr.lagom"
version in ThisBuild := "1.0.0"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.12"

// Disable Cassandra and Kafka
lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false

val swaggerAnnotations = "io.swagger.core.v3" % "swagger-annotations" % "2.0.7"
val lagomOpenapiApi = "org.taymyr.lagom" %% "lagom-openapi-scala-api" % "1.3.0"
val lagomOpenapiImpl = "org.taymyr.lagom" %% "lagom-openapi-scala-impl" % "1.3.0"
val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % Provided
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lazy val `lagom-openapi-scala-demo` = (project in file("."))
  .aggregate(`lagom-openapi-scala-demo-api`, `lagom-openapi-scala-demo-impl`)

lazy val `lagom-openapi-scala-demo-api` = (project in file("api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      swaggerAnnotations,
      lagomOpenapiApi
    )
  )

lazy val `lagom-openapi-scala-demo-impl` = (project in file("impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      lagomOpenapiImpl,
      lagomScaladslTestKit,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`lagom-openapi-scala-demo-api`)
