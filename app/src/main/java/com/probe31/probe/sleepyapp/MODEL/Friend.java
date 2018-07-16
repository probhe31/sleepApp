package com.probe31.probe.sleepyapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friend {

    @SerializedName("idUser")
    @Expose
    private String idUser;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("isAwake")
    @Expose
    private boolean isAwake;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }
}
