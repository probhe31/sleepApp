package com.probe31.probe.sleepyapp.VIEW;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.R;


import java.util.List;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView usernameText;
        TextView statusText;
        TextView isAwakeText;

        public ViewHolder(View view) {
            super(view);
            usernameText = (TextView)view.findViewById(R.id.text_adapter_main_username);
            statusText = (TextView)view.findViewById(R.id.text_adapter_main_status);
            isAwakeText = (TextView)view.findViewById(R.id.text_adapter_main_isawake);
        }
    }

    List<Friend> friendList;

    public FriendRecyclerAdapter(List<Friend> userList) {
        this.friendList = userList;
    }

    @Override
    public FriendRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_adapter, parent, false);

        return new FriendRecyclerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendRecyclerAdapter.ViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.usernameText.setText(friend.getUserName());
        holder.statusText.setText(friend.getStatus());
        holder.isAwakeText.setText(friend.isAwake()?"Despiesto" :"Dormido");
    }




    public void setAccountList(List<Friend> userList) {
        this.friendList = userList;
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        if(friendList != null){
            return friendList.size();
        }
        return 0;

    }

}
