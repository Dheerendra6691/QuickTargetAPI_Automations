package com.restassured.models.request;

public class TokenRequest {

    private final String username;
    private final String password;

    private TokenRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "TokenRequest [username=" + username +
                ", password=" + password + "]";
    }

    public static class Builder {

        private String username;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public TokenRequest build() {

            if (username == null || password == null) {
                throw new IllegalArgumentException(
                        "username/password required");
            }

            return new TokenRequest(this);
        }
    }
}