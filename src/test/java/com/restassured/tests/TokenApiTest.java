package com.restassured.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.client.AuthService;

public class TokenApiTest {

    private final AuthService authService = new AuthService();

    @Test(description = "Verify valid token generation", groups = { "sanity", "regression"
    })

    public void shouldGenerateTokenWhenCredentialsAreValid() {
        String token = authService.generateToken("vikas", "Vikas@123");
        Assert.assertNotNull(token);
        Assert.assertFalse(token.isBlank());
    }

    @Test(groups = { "regression", "smoke" })

    public void shouldFailTokenGenerationWhenCredentialsValidUserAndInvalidPass() {
        try {
            authService.generateToken("vikas", "Wrong@34");
            Assert.fail("Token should not be generated");
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

    @DataProvider(name = "invalidTokenData")
    public Object[][] invalidTokenData() {
        return new Object[][] {
                { "invalid", "Vikas@123" },
                { "vikas", "Wrong@34" },
                { "", "Vikas@123" },
                { "vikas", "" },
                { "", "" } };
    }

    @Test(dataProvider = "invalidTokenData", description = "Verify invalid token generation", groups = {
            "regression", "smoke" })
    public void shouldFailTokenGenerationWhenCredentialsAreInvalid(String username, String password) {
        try {
            authService.generateToken(username, password);
            Assert.fail("Token should not be generated");
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }
}