package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterUserAPIService {

    @POST("api/register")
    Call<RegisterResponse> registerUser(@Body User user);
}
