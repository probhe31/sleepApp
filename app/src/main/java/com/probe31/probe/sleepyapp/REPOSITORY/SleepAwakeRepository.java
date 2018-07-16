package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;

import com.probe31.probe.sleepyapp.MODEL.AwakeResponse;

import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.SleepResponse;
import com.probe31.probe.sleepyapp.WEB_SERVICE.AwakeAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RegisterUserAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import com.probe31.probe.sleepyapp.WEB_SERVICE.SleepAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SleepAwakeRepository {

    private SleepAPIService sleepAPIService;
    private AwakeAPIService awakeAPIService;

    public MutableLiveData<SleepResponse> sleep(String token){

        final MutableLiveData<SleepResponse> sleepMutableLiveData = new MutableLiveData<>();
        sleepAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(SleepAPIService.class);
        Call<SleepResponse> call = sleepAPIService.sleep();
        call.enqueue(new Callback<SleepResponse>() {
            @Override
            public void onResponse(Call<SleepResponse> call, Response<SleepResponse> response) {
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
}
