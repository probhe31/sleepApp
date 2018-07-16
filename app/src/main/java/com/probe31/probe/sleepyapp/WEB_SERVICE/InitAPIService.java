package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InitAPIService {

    @POST("api/init")
    Call<InitResponse> awake(@Body InitResponse initResponse);

}
