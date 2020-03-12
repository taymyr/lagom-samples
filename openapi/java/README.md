# Lagom OpenAPI Demo: How do I generate OpenAPI Specification for Lagom service?

This example demonstrates, how you can generate [OpenAPI Specification](https://swagger.io/specification/) for Lagom service. It uses the [Lagom OpenAPI](https://github.com/taymyr/lagom-openapi) library.

## About service

This is a simple **Petstor** service, which is often used as examples for demonstrating capabilities _OpenAPI/Swagger_ specifications.

## Testing the example

#### unit tests

You can test this example using the provided tests:

```bash
./mvnw verify
```

#### manual tests

You can also test this example manually:

1. Open terminal and start the service

```bash
./mvnw lagom:runAll
```

2. Open next link in the browser to get OpenAPI Specification:

```
http://localhost:9000/_pets/openapi[?format=json|yaml]
```
