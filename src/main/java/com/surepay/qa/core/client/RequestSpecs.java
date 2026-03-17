package com.surepay.qa.core.client;

import com.surepay.qa.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public final class RequestSpecs {

    private RequestSpecs() {
    }

    public static RequestSpecification defaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getConfig().getBaseUrl())
                .setContentType(ContentType.JSON)
                .build();
    }
}