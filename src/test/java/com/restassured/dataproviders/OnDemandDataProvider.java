package com.restassured.dataproviders;

import org.testng.annotations.DataProvider;

import com.restassured.models.request.OnDemandRequest;

/*
Provides test data from external sources dynamically.
Supports data-driven API testing.
Helps execute multiple input combinations efficiently.
*/
public final class OnDemandDataProvider {

    private OnDemandDataProvider() {
    }

    @DataProvider(name = "positiveOnDemandData")
    public static Object[][] positiveOnDemandData() {

        return new Object[][] {
                { new OnDemandRequest.Builder().build() },
                { new OnDemandRequest.Builder().meterNo("DL0010003").build() },
                { new OnDemandRequest.Builder().commandType(4).build() },
                { new OnDemandRequest.Builder().commandValue(2).build() },
                { new OnDemandRequest.Builder().commandValue(4).meterNo("DL0010007").build() }
        };
    }

    @DataProvider(name = "negativeOnDemandData")
    public static Object[][] negativeOnDemandData() {

        return new Object[][] {
                { new OnDemandRequest.Builder().meterNo("979").build(), 200 },
                { new OnDemandRequest.Builder().commandType(8768).build(), 200 },
                { new OnDemandRequest.Builder().commandValue(9696).build(), 200 },
                { new OnDemandRequest.Builder().requestId(9687).build(), 404 },
                { new OnDemandRequest.Builder().requestId(-3).build(), 404 }
        };
    }

}
