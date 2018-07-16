package com.probe31.probe.sleepyapp.VIEW_MODEL;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.User;
import com.probe31.probe.sleepyapp.REPOSITORY.RegisterUserRepository;

public class RegisterUserActivityViewModel extends ViewModel{

    private MutableLiveData<RegisterResponse> responseLiveData;
    private RegisterUserRepository registerUserRepository;

    public RegisterUserActivityViewModel() {
        responseLiveData = new MutableLiveData<>();
        responseLiveData.setValue(null);
        registerUserRepository = new RegisterUserRepository();
    }

    public LiveData<RegisterResponse> registerUser(User user){
        responseLiveData = registerUserRepository.registerUserResponseLiveData(user);
        return responseLiveData;
    }

    public boolean isUserNameCorrect(String username)
    {
        return !TextUtils.isEmpty(username) && TextUtils.getTrimmedLength(username)>3;
    }

    public boolean isPasswordCorrect(String password)
    {
        return !TextUtils.isEmpty(password) && TextUtils.getTrimmedLength(password)>4;
    }

}
