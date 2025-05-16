package de.nonbi.api.route;

import de.nonbi.api.annotations.Status;
import de.nonbi.api.annotations.Route;
import de.nonbi.api.annotations.RouteHandler;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

public class AutoRouteManager {

    public static void registerRoutes(Javalin app, String basePackage, boolean debug) {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<? extends RouteHandler>> routeClasses = reflections.getSubTypesOf(RouteHandler.class);

        for (Class<?> clazz : routeClasses) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Route.class)) {
                    Route route = method.getAnnotation(Route.class);

                    Handler handler = null;
                    try {
                        handler = ctx -> method.invoke(null, ctx); // assumes static methods
                    } catch (Exception e) {
                        if (debug) {
                            throw new RuntimeException("Fehler beim Registrieren von " + method.getName(), e);
                        }
                    }

                    switch (route.method().getValue()) {
                        case "GET" -> app.get(route.path(), handler);
                        case "POST" -> app.post(route.path(), handler);
                        case "PUT" -> app.put(route.path(), handler);
                        case "DELETE" -> app.delete(route.path(), handler);
                        default -> {
                            if (debug) {
                                throw new IllegalArgumentException("Unsupported method: " + route.method());
                            }
                        }
                    }
                    if (debug) {
                        System.out.println("Registered route: [" + route.method() + "] " + route.path());
                    }
                }
                if (method.isAnnotationPresent(Status.class)) {
                    Status route = method.getAnnotation(Status.class);

                    Handler handler = null;
                    try {
                        handler = ctx -> method.invoke(null, ctx); // assumes static methods
                    } catch (Exception e) {
                        if (debug) {
                            throw new RuntimeException("Fehler beim Registrieren von " + method.getName(), e);
                        }
                    }

                    app.error(route.status(), handler);
                    if (debug) {
                        System.out.println("Registered status: [" + route.status() + "]");
                    }
                }
            }
        }
    }
}

