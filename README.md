# Javalin Route Registry Addon

This addon allows you to define HTTP routes in Javalin using the `@Route` annotation. All classes that implement the `RouteManager` interface are automatically detected and their annotated methods are registered on startup.

## Features

- Define routes using annotations
- Automatically registers routes on application startup
- Supports different HTTP methods
- Simple structure for modular APIs

## Installation

1. Build the project as a `.jar`.
2. Add it to your project:

```groovy
dependencies {
    implementation files('libs/RouteRegistryAddon.jar')
}
```
#
### Registering Routes Automatically
Use AutoRouteManager` to scan and register your annotated route classes:
```java
public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7002);
        AutoRouteManager.registerRoutes(app, "de.nonbi.api.controller", false);
    }
}
```
## Usage Examples
### Defining a Route
Create a controller class and annotate your route method:
```java
public class ExampleController implements RouteHandler {
    
    @Route(path = "/example", method = Method.GET)
    public static void exampleFunc(Context ctx) {
        ctx.json("Example");
    }
}
```
#
### Handling Status Codes
You can also define custom responses for specific status codes:
```java
public class ExampleController implements RouteHandler {

    @Status(status = 404)
    public static void exampleStatus(Context ctx) {
        ctx.json("Page Not Found");
    }
}
```
#

## License
This project is open-source and available under the MIT License.