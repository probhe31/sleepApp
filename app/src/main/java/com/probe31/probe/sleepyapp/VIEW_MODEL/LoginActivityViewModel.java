package com.probe31.probe.sleepyapp.VIEW_MODEL;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.util.Log;

import com.probe31.probe.sleepyapp.MODEL.TokenRequest;
import com.probe31.probe.sleepyapp.MODEL.TokenResponse;
import com.probe31.probe.sleepyapp.REPOSITORY.LoginRepository;

public class LoginActivityViewModel extends ViewModel {

    private MutableLiveData<TokenResponse> tokenResponse;
    private LoginRepository loginRepository;

    public LoginActivityViewModel(){
        tokenResponse = new MutableLiveData<>();
        tokenResponse.setValue(null);
        loginRepository = new LoginRepository();
    }

    public LiveData<TokenResponse> getTokenResponse()
    {
        return tokenResponse;
    }

    public LiveData<Integer> checkNetwork(){
        return loginRepository.getResponseCodeLiveData();
    }

    public LiveData<TokenResponse> tryLogin(String username, String password){

        Log.d("debug_probe31", "enter view model");

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUsername(username);
        tokenRequest.setPassword(password);
        tokenResponse = loginRepository.getTokenResponseLiveData(tokenRequest);
        return tokenResponse;
    }

    public boolean validateUsername(String username){
        return !TextUtils.isEmpty(username);
    }

    public boolean validatePassword(String password){
        return !TextUtils.isEmpty(password);
    }
}
