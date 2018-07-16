package com.probe31.probe.sleepyapp.REPOSITORY;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.MODEL.SearchResponse;
import com.probe31.probe.sleepyapp.WEB_SERVICE.FriendListAPIService;
import com.probe31.probe.sleepyapp.WEB_SERVICE.RetrofitClientInstance;
import com.probe31.probe.sleepyapp.WEB_SERVICE.SearchFriendAPIService;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendRepository {

    private FriendListAPIService friendListAPIService;
    private SearchFriendAPIService searchFriendAPIService;

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


    public MutableLiveData<SearchResponse> searchFriends(String token, String criteria){

        final MutableLiveData<SearchResponse> friendsResponseLiveData = new MutableLiveData<>();
        searchFriendAPIService =  RetrofitClientInstance.getRetrofitInstance(token).create(SearchFriendAPIService.class);

        Call<SearchResponse> call = searchFriendAPIService.searchFriends(criteria);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                Log.d("debug_bherring", response.toString());

                SearchResponse friends = response.body();
                Log.d("debug_bherring 2 ", friends.getResults().size()+ "  " +friends.getResults().get(0).getUserName().toString());

                if(friends!=null){
                    friendsResponseLiveData.setValue(friends);
                }else {
                    friendsResponseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                friendsResponseLiveData.setValue(null);

            }
        });

        return friendsResponseLiveData;

    }


}
