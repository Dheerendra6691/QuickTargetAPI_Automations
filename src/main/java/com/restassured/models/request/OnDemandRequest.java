package com.restassured.models.request;

/*
 * Immutable request POJO for On Demand API payload.
 * Implemented using Builder Pattern for clean, readable, and flexible
 * object creation, allowing field assignment in any order while
 * improving maintainability, scalability, and test data management.
 */

public final class OnDemandRequest {

    private final Integer requestId;
    private final String meterNo;
    private final Integer commandType;
    private final Object commandValue;

    private OnDemandRequest(Builder builder) {
        this.requestId = builder.requestId;
        this.meterNo = builder.meterNo;
        this.commandType = builder.commandType;
        this.commandValue = builder.commandValue;
    }

    // Getters for JSON serialization, assertions, logging, and framework
    // compatibility.
    public Integer getRequestId() {
        return requestId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public Integer getCommandType() {
        return commandType;
    }

    public Object getCommandValue() {
        return commandValue;
    }

    // Builder entry point
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "OnDemandRequest{" +
                "requestId=" + requestId +
                ", meterNo='" + meterNo + '\'' +
                ", commandType=" + commandType +
                ", commandValue=" + commandValue +
                '}';
    }

    // Builder class helps to assign values in any order.
    public static final class Builder {

        private Integer requestId;
        private String meterNo;
        private Integer commandType;
        private Object commandValue;

        private Builder() {
        }

        // Fluent setters for readable method chaining.
        public Builder requestId(Integer requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder meterNo(String meterNo) {
            this.meterNo = meterNo;
            return this;
        }

        public Builder commandType(Integer commandType) {
            this.commandType = commandType;
            return this;
        }

        public Builder commandValue(Object commandValue) {
            this.commandValue = commandValue;
            return this;
        }

        // Validation + object creation
        public OnDemandRequest build() {
            return new OnDemandRequest(this);
        }
    }
}