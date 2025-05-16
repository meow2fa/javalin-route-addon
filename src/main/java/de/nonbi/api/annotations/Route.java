package de.nonbi.api.annotations;

import de.nonbi.api.http.Method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    String path(); // Route path
    Method method() default Method.GET; // Optional: GET/POST/PUT/DELETE
}