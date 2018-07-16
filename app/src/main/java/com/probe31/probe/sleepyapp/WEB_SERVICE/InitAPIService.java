package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InitAPIService {

    @GET("api/initData")
    Call<InitResponse> getInitData();

}
