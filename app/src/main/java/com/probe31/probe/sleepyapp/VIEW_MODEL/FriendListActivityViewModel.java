package com.probe31.probe.sleepyapp.VIEW_MODEL;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.MODEL.SearchResponse;
import com.probe31.probe.sleepyapp.REPOSITORY.FriendRepository;;

import java.util.List;

public class FriendListActivityViewModel extends ViewModel {

    private MutableLiveData<List<Friend>> friendsReponse;
    private MutableLiveData<SearchResponse> friendsSearchReponse;
    private FriendRepository friendRepository;

    public FriendListActivityViewModel() {

        friendsReponse = new MutableLiveData<>();
        friendsReponse.setValue(null);

        friendsSearchReponse = new MutableLiveData<>();
        friendsSearchReponse.setValue(null);


        friendRepository = new FriendRepository();
    }

    public MutableLiveData<List<Friend>> getFriends(String token)
    {
        friendsReponse = friendRepository.getFriends(token);
        return friendsReponse;
    }

    public MutableLiveData<SearchResponse> searchFriends(String token, String criteria)
    {
        friendsSearchReponse = friendRepository.searchFriends(token, criteria);
        return friendsSearchReponse;
    }
}
