package com.restassured.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestOnDemandResponse {

    @JsonProperty("command_type")
    private int commandType;
    @JsonProperty("command_value")
    private String commandValue;
    @JsonProperty("request_id")
    private int requestId;
    @JsonProperty("meter_no")
    private String meterNo;

    private int id;
    private String status;

    @JsonProperty("created_at")
    private String createdAt;

    public int getCommandType() {
        return commandType;
    }

    public String getCommandValue() {
        return commandValue;
    }

    public int getRequestId() {
        return requestId;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}