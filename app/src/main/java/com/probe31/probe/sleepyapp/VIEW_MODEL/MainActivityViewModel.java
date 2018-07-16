package com.probe31.probe.sleepyapp.VIEW_MODEL;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import com.probe31.probe.sleepyapp.REPOSITORY.InitDataRepository;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<InitResponse> initResponseLiveData;
    private InitDataRepository initDataRepository;

    public MainActivityViewModel() {
        initResponseLiveData = new MutableLiveData<>();
        initResponseLiveData.setValue(null);
        initDataRepository = new InitDataRepository();
    }

    public LiveData<InitResponse> getInitData(String token){

        initResponseLiveData = initDataRepository.getInitData(token);
        return initResponseLiveData;

    }


}
