package de.nonbi.api.controller;

import de.nonbi.api.annotations.Route;
import de.nonbi.api.annotations.RouteHandler;
import de.nonbi.api.annotations.Status;
import de.nonbi.api.http.Method;
import io.javalin.http.Context;

public class ExampleController implements RouteHandler {

    @Status(status = 404)
    public static void exampleStatus(Context ctx) {
        ctx.json("Page Not Found");
    }
}
