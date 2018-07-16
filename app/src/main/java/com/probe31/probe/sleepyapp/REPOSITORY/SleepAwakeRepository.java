package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.probe31.probe.sleepyapp.MODEL.AwakeResponse;

import com.probe31.probe.sleepyapp.MODEL.MessageRequest;
import com.probe31.probe.sleepyapp.MODEL.MessageResponse;
import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.SleepResponse;
import com.probe31.probe.sleepyapp.WEB_SERVICE.AwakeAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.MessageAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RegisterUserAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import com.probe31.probe.sleepyapp.WEB_SERVICE.SleepAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SleepAwakeRepository {

    private SleepAPIService sleepAPIService;
    private AwakeAPIService awakeAPIService;
    private MessageAPIService messageAPIService;

    public MutableLiveData<SleepResponse> sleep(String token){

        final MutableLiveData<SleepResponse> sleepMutableLiveData = new MutableLiveData<>();
        sleepAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(SleepAPIService.class);
        Call<SleepResponse> call = sleepAPIService.sleep();
        call.enqueue(new Callback<SleepResponse>() {
            @Override
            public void onResponse(Call<SleepResponse> call, Response<SleepResponse> response) {

                Log.d("debug_bh_sleep", response.toString());
                if(response.isSuccessful()) {
                    SleepResponse sleepResponse = response.body();
                    if(sleepResponse!=null){
                        sleepMutableLiveData.setValue(sleepResponse);
                    }else{
                        sleepMutableLiveData.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<SleepResponse> call, Throwable t) {
                sleepMutableLiveData.setValue(null);
            }
        });
        return sleepMutableLiveData;
    }

    public MutableLiveData<AwakeResponse> awake(String token){

        final MutableLiveData<AwakeResponse> awakeMutableLiveData = new MutableLiveData<>();
        awakeAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(AwakeAPIService.class);
        Call<AwakeResponse> call = awakeAPIService.awake();
        call.enqueue(new Callback<AwakeResponse>() {
            @Override
            public void onResponse(Call<AwakeResponse> call, Response<AwakeResponse> response) {

                Log.d("debug_bh_awake", response.toString());

                if(response.isSuccessful()) {
                    AwakeResponse awakeResponse = response.body();
                    if(awakeResponse!=null){
                        awakeMutableLiveData.setValue(awakeResponse);
                    }else{
                        awakeMutableLiveData.setValue(null);
                    }

                }

            }

            @Override
            public void onFailure(Call<AwakeResponse> call, Throwable t) {
                awakeMutableLiveData.setValue(null);
            }
        });
        return awakeMutableLiveData;
    }



    public MutableLiveData<MessageResponse> sendMessage(String token, String message){

        final MutableLiveData<MessageResponse> messageMutableLiveData = new MutableLiveData<>();
        messageAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(MessageAPIService.class);
        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setMessage(message);
        Call<MessageResponse> call = messageAPIService.sendMessage(messageRequest);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {

                Log.d("debug_bh_awake", response.toString());

                if(response.isSuccessful()) {
                    MessageResponse messageResponspe = response.body();
                    if(messageResponspe!=null){
                        messageMutableLiveData.setValue(messageResponspe);
                    }else{
                        messageMutableLiveData.setValue(null);
                    }

                }

            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                messageMutableLiveData.setValue(null);
            }
        });
        return messageMutableLiveData;
    }
}
