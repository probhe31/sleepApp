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
    private float sleep_hour;

}
