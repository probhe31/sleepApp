package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;

import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.WEB_SERVICE.FriendListAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {

    private FriendListAPIService friendListAPIService;

    public MutableLiveData<List<Friend>> getFriends(String token){

        final MutableLiveData<List<Friend>> friendsResponseLiveData = new MutableLiveData<>();
        friendListAPIService =  RetrofitClientInstance.getRetrofitInstance(token).create(FriendListAPIService.class);

        Call<List<Friend>> call = friendListAPIService.getFriends();
        call.enqueue(new Callback<List<Friend>>() {
            @Override
            public void onResponse(Call<List<Friend>> call, Response<List<Friend>> response) {

                List<Friend> friends = response.body();

                if(friends!=null){
                    friendsResponseLiveData.setValue(friends);
                }else {
                    friendsResponseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Friend>> call, Throwable t) {
                friendsResponseLiveData.setValue(null);

            }
        });

        return friendsResponseLiveData;

    }


}
