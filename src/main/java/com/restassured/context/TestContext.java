package com.restassured.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
Maintains shared test execution data across complete test lifecycle.
Stores tokens, IDs, and runtime values.
Helps coordinate data between services and validations.
*/
public final class TestContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(ConcurrentHashMap::new);

    private TestContext() {
    }

    public static void put(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) CONTEXT.get().get(key);
    }

    public static void remove() {
        CONTEXT.remove();
    }
}