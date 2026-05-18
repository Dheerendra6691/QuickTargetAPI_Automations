package com.restassured.dataproviders;

import org.testng.annotations.DataProvider;

import com.restassured.models.request.TokenRequest;

public class AuthDataProvider {

    @DataProvider(name = "invalidTokenData")
    public static Object[][] invalidTokenData() {

        return new Object[][] {
                {
                        TokenRequest.builder()
                                .username("invalid")
                                .password("Vikas@123")
                                .build()
                },
                {
                        TokenRequest.builder()
                                .username("vikas")
                                .password("Wrong@34")
                                .build()
                }
        };
    }
}