package com.surepay.qa.config;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Environment ENV =
            Environment.from(System.getProperty("env", "qa"));

    private static final TestConfig CONFIG = loadConfig();

    private ConfigManager() {
    }

    public static TestConfig getConfig() {
        return CONFIG;
    }

    private static TestConfig loadConfig() {
        Properties properties = new Properties();
        String resourcePath = String.format("/config/application-%s.properties", ENV.name().toLowerCase());

        try (InputStream inputStream = ConfigManager.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException("Could not load config file: " + resourcePath);
            }

            properties.load(inputStream);

            return new TestConfig(
                    properties.getProperty("base.url"),
                    Integer.parseInt(properties.getProperty("connect.timeout.ms")),
                    Integer.parseInt(properties.getProperty("read.timeout.ms"))
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration from " + resourcePath, e);
        }
    }
}