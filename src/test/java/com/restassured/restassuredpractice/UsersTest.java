package com.restassured.restassuredpractice;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UsersTest {
    @Test(enabled = false)
    public void testUsersAPi() {
        Response response = BaseClient.getRequest("users");
        int statuCode = response.getStatusCode();
        System.out.println(response.asString());
        assertEquals(statuCode, 200);
        List<String> emailList = response.jsonPath().getList("email");
        System.out.println(emailList);

    }

    @Test(enabled = false)
    public void testUsersCreationsRequestWithMap() {
        Response response = BaseClient.postRequestWithMap("users");
        int statuCode = response.getStatusCode();
        String contentType = response.contentType();
        String name = response.jsonPath().getString("name");
        System.out.println(response.asPrettyString());
        System.out.println("yyyyyyyyyyyyyyy" + name);
        assertEquals(statuCode, 201);
        assertEquals(contentType, "application/json; charset=utf-8");

    }

    @Test(enabled = false)
    public void testUsersCreationsRequestWithBuilder() {
        String email = "piyush" + System.currentTimeMillis() + "@gmail.com";
        String name = "piyush" + System.currentTimeMillis();
        UserRequest userPayload = UserRequest.builder()
                .name(name)
                .email(email)
                .gender("male")
                .status("active")
                .build();
        Response response = BaseClient.postRequestWithBuilder("users", userPayload);

        // Builder object validation using getters [Did Builder store "active"
        // correctly?]
        assertEquals(userPayload.getEmail(), email);
        // Response body validation
        assertEquals(response.getStatusCode(), 201);
        assertEquals(response.jsonPath().getString("email"),
                userPayload.getEmail());

    }

    @Test(enabled = true)
    public void testUsersCreationsRequestWithBuilderAndRequestSpecs() {
        String email = "piyush" + System.currentTimeMillis() + "@gmail.com";
        String name = "piyush" + System.currentTimeMillis();
        UserRequest userPayload = UserRequest.builder()
                .name(name)
                .email(email)
                .gender("male")
                .status("active")
                .build();
        Response response = BaseClient.postRequestWithRequestSpecs("users", userPayload);

        System.out.println(response.asPrettyString());

        // Builder object validation using getters [Did Builder store "active"
        // correctly?]
        assertEquals(userPayload.getEmail(), email);
        // Response body validation
        assertEquals(response.getStatusCode(), 201);
        assertEquals(
                response.jsonPath().getString("email"),
                userPayload.getEmail());

    }

    @Test(enabled = true)
    public void testUsersCreationsRequestWithRequestAndReponseSpecs() {
        String email = "piyush" + System.currentTimeMillis() + "@gmail.com";
        String name = "piyush" + System.currentTimeMillis();
        UserRequest userPayload = UserRequest.builder()
                .name(name)
                .email(email)
                .gender("male")
                .status("active")
                .build();
        Response response = BaseClient.postRequestWithRequestAndReponseSpecs("users", userPayload);

        System.out.println(response.asPrettyString());

        // Builder object validation using getters [Did Builder store "active"
        // correctly?]
        assertEquals(userPayload.getEmail(), email);

        assertEquals(
                response.jsonPath().getString("email"),
                userPayload.getEmail());

    }

}


