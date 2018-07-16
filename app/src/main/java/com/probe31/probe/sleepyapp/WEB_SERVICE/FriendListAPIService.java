package com.probe31.probe.sleepyapp.WEB_SERVICE;

import com.probe31.probe.sleepyapp.MODEL.Friend;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FriendListAPIService {

    @GET("api/friends")
    Call<List<Friend>> getFriends();
}
