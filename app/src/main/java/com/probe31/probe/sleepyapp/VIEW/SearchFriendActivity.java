package com.probe31.probe.sleepyapp.VIEW;

        import android.arch.lifecycle.Observer;
        import android.arch.lifecycle.ViewModelProviders;
        import android.content.SharedPreferences;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.TextView;

        import com.probe31.probe.sleepyapp.MODEL.Friend;
        import com.probe31.probe.sleepyapp.MODEL.SearchResponse;
        import com.probe31.probe.sleepyapp.R;
        import com.probe31.probe.sleepyapp.VIEW_MODEL.FriendListActivityViewModel;

        import java.util.List;

public class SearchFriendActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FriendRecyclerAdapter friendRecyclerAdapter;
    FriendListActivityViewModel friendListActivityVM;
    List<Friend> friendList;
    TextView textSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);

        friendListActivityVM = ViewModelProviders.of(this).get(FriendListActivityViewModel.class);
        this.textSearch = findViewById(R.id.text_search_search);
        //fillRecyclerView();
    }

    void fillRecyclerView()
    {
        recyclerView = findViewById(R.id.rv_search_friend_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        friendRecyclerAdapter = new FriendRecyclerAdapter(friendList);
        recyclerView.setAdapter(friendRecyclerAdapter);

        SharedPreferences settings = getSharedPreferences("userData", 0);
        String token = settings.getString("token", "");

        friendListActivityVM.searchFriends(token, textSearch.getText().toString()).observe(this, new Observer<SearchResponse>() {
            @Override
            public void onChanged(@Nullable SearchResponse friendListResponse) {

                if(friendListResponse!=null)
                {
                    friendList= friendListResponse.getResults();
                    friendRecyclerAdapter.setAccountList(friendList);
                }
            }
        });
    }

    public void onClickSearch(View view)
    {
        fillRecyclerView();
    }




}