package com.restassured.context;

import java.util.HashMap;
import java.util.Map;

/* 
Stores temporary test data during single scenario execution. 
Allows data sharing between API calls. 
Helps validate multi-step workflows and chained API scenarios.
*/
public final class ScenarioContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(HashMap::new);

    private ScenarioContext() {
    }

    public static void set(
            String key,
            Object value) {

        CONTEXT.get()
                .put(
                        key,
                        value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(
            String key) {

        return (T) CONTEXT.get()
                .get(
                        key);
    }

    public static boolean contains(
            String key) {

        return CONTEXT.get()
                .containsKey(
                        key);
    }

    public static void clear() {

        CONTEXT.remove();
    }
}