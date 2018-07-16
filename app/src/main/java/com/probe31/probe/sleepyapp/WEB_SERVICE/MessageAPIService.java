package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.MessageRequest;
import com.probe31.probe.sleepyapp.MODEL.MessageResponse;
import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageAPIService {

    @POST("api/registerState")
    Call<MessageResponse> sendMessage(@Body MessageRequest message);
}
