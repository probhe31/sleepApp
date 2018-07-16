package com.probe31.probe.sleepyapp.VIEW;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.R;
import com.probe31.probe.sleepyapp.VIEW_MODEL.FriendListActivityViewModel;

import java.util.List;

public class FriendsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FriendRecyclerAdapter friendRecyclerAdapter;
    FriendListActivityViewModel friendListActivityVM;
    List<Friend> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        friendListActivityVM = ViewModelProviders.of(this).get(FriendListActivityViewModel.class);

        fillRecyclerView();
    }

    void fillRecyclerView()
    {
        recyclerView = findViewById(R.id.rv_friend_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        friendRecyclerAdapter = new FriendRecyclerAdapter(friendList);
        recyclerView.setAdapter(friendRecyclerAdapter);

        SharedPreferences settings = getSharedPreferences("userData", 0);
        String token = settings.getString("token", "");

        friendListActivityVM.getFriends(token).observe(this, new Observer<List<Friend>>() {
            @Override
            public void onChanged(@Nullable List<Friend> friendListResponse) {

                if(friendListResponse!=null)
                {
                    friendList= friendListResponse;
                    friendRecyclerAdapter.setAccountList(friendList);
                }
            }
        });
    }




}
