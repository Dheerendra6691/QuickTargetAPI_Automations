package com.restassured.models.request;

/*
 * Immutable request POJO for authentication/token generation API payload.
 * Implemented using Builder Pattern for clean object creation with
 * mandatory field validation.
 */
public final class TokenRequest {

    private final String username;
    private final String password;

    private TokenRequest(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "TokenRequest [username=" + username
                + ", password=******]";
    }

    public static final class Builder {

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

            if (isBlank(username) || isBlank(password)) {
                throw new IllegalArgumentException(
                        "Username and password must not be null or blank");
            }

            return new TokenRequest(this);
        }

        private boolean isBlank(String value) {
            return value == null || value.trim().isEmpty();
        }
    }
}