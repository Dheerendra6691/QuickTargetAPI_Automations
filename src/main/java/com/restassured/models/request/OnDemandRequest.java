package com.restassured.models.request;

/*
 * Immutable request POJO for On Demand API payload.
 * Implemented using Builder Pattern for clean, readable, and flexible
 * object creation, allowing field assignment in any order while
 * improving maintainability, scalability, and test data management.
 */
public class OnDemandRequest {

    private final Integer request_id;
    private final String meter_no;
    private final Integer command_type;
    private final Object command_value;

    private OnDemandRequest(Builder builder) {
        this.request_id = builder.request_id;
        this.meter_no = builder.meter_no;
        this.command_type = builder.command_type;
        this.command_value = builder.command_value;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public String getMeter_no() {
        return meter_no;
    }

    public Integer getCommand_type() {
        return command_type;
    }

    public Object getCommand_value() {
        return command_value;
    }

    @Override
    public String toString() {
        return "OnDemandRequest [request_id=" + request_id + ", meter_no=" + meter_no + ", command_type=" + command_type
                + ", command_value=" + command_value + "]";
    }

    // Builder class help to assign value in any order
    public static class Builder {

        private Integer request_id;
        private String meter_no;
        private Integer command_type;
        private Object command_value;

        public Builder requestId(Integer requestId) {
            this.request_id = requestId;
            return this;
        }

        public Builder meterNo(String meterNo) {
            this.meter_no = meterNo;
            return this;
        }

        public Builder commandType(Integer commandType) {
            this.command_type = commandType;
            return this;
        }

        public Builder commandValue(Object commandValue) {
            this.command_value = commandValue;
            return this;
        }

        public OnDemandRequest build() {
            return new OnDemandRequest(this);
        }
    }
}