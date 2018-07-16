package com.probe31.probe.sleepyapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SleepResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("status_label")
    @Expose
    private String status_label;

    public int getStatus() {
        return status;
    }

    public String getStatus_label() {
        return status_label;
    }
}
