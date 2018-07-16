package com.probe31.probe.sleepyapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitResponse {

    @SerializedName("status")
    @Expose
    private int status ;
    @SerializedName("status_label")
    @Expose
    private String status_label;
    @SerializedName("sleep_hour")
    @Expose
    private String sleep_hour;
    @SerializedName("message")
    @Expose
    private String message;
    public int getStatus() {
        return status;
    }

    public String getStatus_label() {
        return status_label;
    }

    public String getSleep_hour() {
        return sleep_hour;
    }

    public String getMessage() {
        return message;
    }
}
