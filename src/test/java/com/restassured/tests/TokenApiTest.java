package com.restassured.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restassured.dataproviders.AuthDataProvider;
import com.restassured.models.request.TokenRequest;
import com.restassured.workflows.AuthWorkflow;

public class TokenApiTest {

    private final AuthWorkflow authWorkflow = new AuthWorkflow();

    @Test(description = "Verify valid token generation", groups = { "sanity", "regression" })
    public void shouldGenerateTokenWhenCredentialsAreValid() {
        // Hard coded
        TokenRequest request = TokenRequest.builder()
                .username("vikas")
                .password("Vikas@123")
                .build();

        String token = authWorkflow.generateToken(request);

        Assert.assertNotNull(token, "Generated token should not be null");
        Assert.assertFalse(token.isBlank(), "Generated token should not be blank");
    }

    @Test(dataProvider = "invalidTokenData", dataProviderClass = AuthDataProvider.class, groups = { "smoke",
            "regression" })
    public void shouldFailTokenGenerationWhenCredentialsAreInvalid(TokenRequest request) {

        try {

            authWorkflow.generateToken(request);

            Assert.fail("Token should not be generated for invalid credentials");

        } catch (RuntimeException exception) {

            Assert.assertNotNull(exception.getMessage(), "Exception message should be present");
        }
    }
}