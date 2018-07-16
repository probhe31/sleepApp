package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;

import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.User;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RegisterUserAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserRepository {

    private RegisterUserAPIService registerUserAPIService;

    public RegisterUserRepository(){

    }

    public MutableLiveData<RegisterResponse> registerUserResponseLiveData(final User user) {

        final MutableLiveData<RegisterResponse> registerUserMutableLiveData = new MutableLiveData<>();

        registerUserAPIService = RetrofitClientInstance.getRetrofitInstance().create(RegisterUserAPIService.class);
        Call<RegisterResponse> call = registerUserAPIService.registerUser(user);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if(response.isSuccessful())
                {
                    RegisterResponse registerReponse = response.body();

                    if(registerReponse != null) {
                        registerUserMutableLiveData.setValue(registerReponse);
                    }else{
                        registerUserMutableLiveData.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerUserMutableLiveData.setValue(null);

            }
        });

        return registerUserMutableLiveData;

    }



}
