package com.restassured.models.request;

public class TokenRequest {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private final TokenRequest request;

        public Builder() {
            request = new TokenRequest();
        }

        public Builder username(String username) {
            request.setUsername(username);
            return this;
        }

        public Builder password(String password) {
            request.setPassword(password);
            return this;
        }

        public TokenRequest build() {
            return request;
        }
    }
}