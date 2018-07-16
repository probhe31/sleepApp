package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.probe31.probe.sleepyapp.MODEL.TokenRequest;
import com.probe31.probe.sleepyapp.MODEL.TokenResponse;
import com.probe31.probe.sleepyapp.WEB_SERVICE.LoginAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private LoginAPIService loginAPIService;
    private MutableLiveData<Integer> reponseCodeLiveData;

    public LoginRepository(){
        reponseCodeLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getResponseCodeLiveData() {
        return reponseCodeLiveData;
    }

    public MutableLiveData<TokenResponse> getTokenResponseLiveData(final TokenRequest tokenRequest) {

        final MutableLiveData<TokenResponse> tokenResponseMutableLiveData = new MutableLiveData<>();
        loginAPIService = RetrofitClientInstance.getRetrofitInstance().create(LoginAPIService.class);
        Call<TokenResponse> call = loginAPIService.getTokenAccess(tokenRequest);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                reponseCodeLiveData.setValue(response.code());
                Log.d("bherring" , response.toString());
                if(response.isSuccessful())
                {
                    TokenResponse tokenResponse = response.body();

                    if(tokenResponse != null){
                        tokenResponseMutableLiveData.setValue(tokenResponse);
                    }
                    else
                    {
                        if(response.code()==HttpURLConnection.HTTP_UNAUTHORIZED){
                            tokenResponseMutableLiveData.setValue(null);

                        }else{
                            tokenResponseMutableLiveData.setValue(null);
                        }
                    }
                }else
                {
                    tokenResponseMutableLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

                tokenResponseMutableLiveData.setValue(null);
            }
        });

        return tokenResponseMutableLiveData;

    }

}
