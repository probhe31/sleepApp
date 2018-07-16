package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.TokenRequest;
import com.probe31.probe.sleepyapp.MODEL.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPIService {

    @POST("api/login")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);
}
