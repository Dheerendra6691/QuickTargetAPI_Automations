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
                { OnDemandRequest.builder().build() },
                { OnDemandRequest.builder().meterNo("DL0010003").build() },
                { OnDemandRequest.builder().commandType(4).build() },
                { OnDemandRequest.builder().commandValue(2).build() },
                { OnDemandRequest.builder().commandValue(4).meterNo("DL0010007").build() }
        };
    }

    @DataProvider(name = "positiveOnDemandDataCommandType")
    public static Object[][] positiveOnDemandDataCommandType() {

        return new Object[][] {

                { OnDemandRequest.builder().commandType(4).build() },

        };
    }

    @DataProvider(name = "negativeOnDemandData")
    public static Object[][] negativeOnDemandData() {

        return new Object[][] {
                { OnDemandRequest.builder().meterNo("979").build(), 200 },
                { OnDemandRequest.builder().commandType(8768).build(), 200 },
                { OnDemandRequest.builder().commandValue(9696).build(), 200 },
                { OnDemandRequest.builder().requestId(9687).build(), 404 },
                { OnDemandRequest.builder().requestId(-3).build(), 404 }
        };
    }

}
