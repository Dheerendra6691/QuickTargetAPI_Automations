package com.restassured.constants;

/* 
Stores all API endpoint paths as reusable constants. 
Prevents hardcoded URLs in test scripts. 
Helps improve maintainability and reduces endpoint management effort.
*/
public final class ApiEndpoints {

    private ApiEndpoints() {
    }

    public static final String TOKEN = "/token";

    public static final String REQUEST_ON_DEMAND = "/api/HES/RequestOnDemandData";
}