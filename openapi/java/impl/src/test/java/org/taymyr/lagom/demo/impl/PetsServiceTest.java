package org.taymyr.lagom.demo.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.common.net.MediaType;
import com.lightbend.lagom.javadsl.api.transport.MessageProtocol;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.TestServer;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.startServer;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import static java.util.concurrent.TimeUnit.SECONDS;

class PetsServiceTest {

    private static final MessageProtocol YAML = new MessageProtocol().withContentType("application/x-yaml");

    private static TestServer server;

    private static WSClient ws;

    @SuppressWarnings({"UnstableApiUsage", "SameParameterValue"})
    static String resourceAsString(String resourceName) {
        try {
            return Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }

    static <T> T eventually(CompletionStage<T> stage) throws InterruptedException, ExecutionException, TimeoutException {
        return stage.toCompletableFuture().get(5, SECONDS);
    }

    static String yamlToJson(String yaml) {
        try {
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            return jsonWriter.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeAll
    static void setUp() {
        server = startServer(defaultSetup().withCluster(false));
        ws = server.injector().instanceOf(WSClient.class);
    }

    @AfterAll
    static void tearDown() {
        if (server != null) server.stop();
    }

    @Test
    void specShouldBeCorrect() throws InterruptedException, ExecutionException, TimeoutException {
        String expected = yamlToJson(resourceAsString("pets.yml"));
        WSResponse response = eventually(ws.url("http://localhost:" + server.port() + "/_pets/openapi").get());
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(YAML.contentType().orElse(null));
        String actual = yamlToJson(response.getBody());
        assertThatJson(actual).isEqualTo(expected);
    }

}
