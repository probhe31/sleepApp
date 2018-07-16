package com.probe31.probe.sleepyapp.VIEW_MODEL;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.sleepyapp.MODEL.AwakeResponse;
import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import com.probe31.probe.sleepyapp.MODEL.SleepResponse;
import com.probe31.probe.sleepyapp.REPOSITORY.InitDataRepository;
import com.probe31.probe.sleepyapp.REPOSITORY.SleepAwakeRepository;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<InitResponse> initResponseLiveData;
    private MutableLiveData<SleepResponse> sleepResponseLiveData;
    private MutableLiveData<AwakeResponse> awakeResponseLiveData;
    private InitDataRepository initDataRepository;
    private SleepAwakeRepository sleepAwakeRepository;

    public MainActivityViewModel() {
        initResponseLiveData = new MutableLiveData<>();
        initResponseLiveData.setValue(null);

        sleepResponseLiveData = new MutableLiveData<>();
        sleepResponseLiveData.setValue(null);

        awakeResponseLiveData = new MutableLiveData<>();
        awakeResponseLiveData.setValue(null);

        initDataRepository = new InitDataRepository();
        sleepAwakeRepository = new SleepAwakeRepository();
    }

    public LiveData<InitResponse> getInitData(String token){

        initResponseLiveData = initDataRepository.getInitData(token);
        return initResponseLiveData;
    }

    public LiveData<SleepResponse> sleep(String token){

        sleepResponseLiveData = sleepAwakeRepository.sleep(token);
        return sleepResponseLiveData;
    }

    public LiveData<AwakeResponse> awake(String token){

        awakeResponseLiveData = sleepAwakeRepository.awake(token);
        return awakeResponseLiveData;
    }



}
