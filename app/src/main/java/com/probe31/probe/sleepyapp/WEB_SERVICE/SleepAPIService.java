package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.SleepRequest;
import com.probe31.probe.sleepyapp.MODEL.SleepResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SleepAPIService {

    @POST("api/sleep")
    Call<SleepResponse> sleep(@Body SleepRequest sleepRequest);

}
