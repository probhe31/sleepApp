package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.AwakeResponse;
import com.probe31.probe.sleepyapp.MODEL.TokenRequest;
import com.probe31.probe.sleepyapp.MODEL.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AwakeAPIService {

    @POST("api/awake")
    Call<AwakeResponse> awake(@Body AwakeResponse awakeResponse);
}
