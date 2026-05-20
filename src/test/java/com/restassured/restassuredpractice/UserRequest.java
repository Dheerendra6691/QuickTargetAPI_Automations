package com.restassured.restassuredpractice;

public final class UserRequest {

    private final String name;
    private final String email;
    private final String gender;
    private final String status;

    private UserRequest(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.gender = builder.gender;
        this.status = builder.status;
    }

    // Getters for JSON serialization, assertions, logging, and framework
    // compatibility.
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    // Builder entry point
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public static final class Builder {

        private String name;
        private String email;
        private String gender;
        private String status;

        private Builder() {
        }

        // Fluent setters-named same as fields for readable method chaining.
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        // Validation + object creation
        public UserRequest build() {

            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }

            return new UserRequest(this);
        }
    }
}