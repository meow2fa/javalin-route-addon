package de.nonbi.api;

import de.nonbi.api.route.AutoRouteManager;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7002);
        AutoRouteManager.registerRoutes(app, "de.nonbi.api.controller", false);
    }
}