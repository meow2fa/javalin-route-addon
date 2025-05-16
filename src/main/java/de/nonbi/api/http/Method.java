package de.nonbi.api.http;

public enum Method {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private final String value;

    Method(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
