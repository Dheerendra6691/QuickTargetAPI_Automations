package com.restassured.tests.smoke;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restassured.client.AuthService;

public class TokenApiTest {

    @DataProvider(name = "tokenData")
    public Object[][] tokenData() {

        return new Object[][] {
                { "vikas", "Vikas@123", true },
                { "invalid", "Vikas@123", false },
                { "vikas", "wrong", false },
                { "", "Vikas@123", false },
                { "vikas", "", false },
                { "", "", false }
        };
    }

    @Test(dataProvider = "tokenData", description = "Verify token API")
    public void verifyTokenGeneration(String username, String password, boolean shouldPass) {

        AuthService authService = new AuthService();

        try {

            String token = authService.generateToken(username, password);

            Assert.assertTrue(shouldPass);
            Assert.assertNotNull(token);
            Assert.assertFalse(token.isBlank());

        } catch (RuntimeException e) {

            Assert.assertFalse(shouldPass);
        }
    }
}