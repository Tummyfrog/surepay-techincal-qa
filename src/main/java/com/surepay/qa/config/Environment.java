package com.surepay.qa.config;

public enum Environment {
    LOCAL,
    QA;

    public static Environment from(String value) {
        for (Environment environment : values()) {
            if (environment.name().equalsIgnoreCase(value)) {
                return environment;
            }
        }
        throw new IllegalArgumentException("Unknown environment: " + value);
    }
}