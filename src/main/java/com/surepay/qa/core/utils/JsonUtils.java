package com.surepay.qa.core.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtils {
    private static final Gson GSON = new Gson();

    private JsonUtils() {
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        Type type = TypeToken.getParameterized(List.class, clazz).getType();
        return GSON.fromJson(json, type);
    }
}
