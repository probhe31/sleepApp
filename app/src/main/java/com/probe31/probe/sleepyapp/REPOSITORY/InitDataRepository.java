package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;
import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import com.probe31.probe.sleepyapp.WEB_SERVICE.InitAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitDataRepository {

    private InitAPIService initAPIService;

    public InitDataRepository() {

    }

    public MutableLiveData<InitResponse> getInitData(String token){

        final MutableLiveData<InitResponse> initDataResponseLiveData = new MutableLiveData<>();

        initAPIService = RetrofitClientInstance.getRetrofitInstance(token).create(InitAPIService.class);
        Call<InitResponse> call = initAPIService.getInitData();
        call.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, Response<InitResponse> response) {

                if(response.isSuccessful())
                {
                    InitResponse initResponse = response.body();

                    if(initResponse != null) {
                        initDataResponseLiveData.setValue(initResponse);
                    }else{
                        initDataResponseLiveData.setValue(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                initDataResponseLiveData.setValue(null);
            }
        });

        return initDataResponseLiveData;
    }

}
