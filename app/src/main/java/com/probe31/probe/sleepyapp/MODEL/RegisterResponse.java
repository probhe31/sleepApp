package com.probe31.probe.sleepyapp.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("idUser")
    @Expose
    public String idUser;

    @SerializedName("username")
    @Expose
    public String username;

    public String getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }
}
