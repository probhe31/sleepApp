package com.probe31.probe.sleepyapp.VIEW;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.probe31.probe.sleepyapp.MODEL.Friend;
import com.probe31.probe.sleepyapp.R;


import java.util.List;
import java.util.Random;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView usernameText;
        TextView messageText;
        TextView statusText;
        TextView isAwakeText;
        TextView hoursText;
        ImageView imageAvatar;

        public ViewHolder(View view) {
            super(view);
            usernameText = (TextView)view.findViewById(R.id.text_adapter_main_username);
            messageText = (TextView)view.findViewById(R.id.text_adapter_message);
            hoursText = (TextView)view.findViewById(R.id.text_time_sleep);
            //statusText = (TextView)view.findViewById(R.id.text_adapter_main_status);
            //isAwakeText = (TextView)view.findViewById(R.id.text_adapter_main_isawake);
            imageAvatar = (ImageView)view.findViewById(R.id.image_main_friend_avatar);
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
        holder.messageText.setText(getRandomMessage());
        holder.hoursText.setText(getHoursByStatus(friend.getStatus()));
        holder.imageAvatar.setImageResource(getStatusImage(friend.getStatus()));
    }


    public String getHoursByStatus(int status){

        //TODO: get data from api
        if(status==7)
            return "10:00";
        else if(status==6)
            return "08:00";
        else if(status==5)
            return "06:00";
        else if(status==4)
            return "04:25";
        else if(status==3)
            return "02:30";
        else if(status==2)
            return "01:30";
        else if(status==1)
            return "00:30";

        return "Durmiendo...";
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

    String getRandomMessage()
    {
        Random r = new Random();
        int i1 = r.nextInt(100 - 1) + 1;
        if(i1<10)
            return "Sobreviviendo ADS";

        if(i1<20)
            return "Heelp!";

        if(i1<40)
            return "Parciales :(";

        if(i1<60)
            return "Soy programador :(";

        if(i1<80)
            return "No termine la API";

        return "Suplicatorio aya voy :D";

    }


    int getStatusImage(int status)
    {
        int result = 0;
        switch (status)
        {
            case 1:
                result = R.drawable.avatar_128_status_7;
                break;
            case 2:
                result = R.drawable.avatar_128_status_6;
                break;
            case 3:
                result = R.drawable.avatar_128_status_8;
                break;
            case 4:
                result = R.drawable.avatar_128_status_4;
                break;
            case 5:
                result = R.drawable.avatar_128_status_3;
                break;
            case 6:
                result = R.drawable.avatar_128_status_5;
                break;
            case 7:
                result = R.drawable.avatar_128_status_1;
                break;
            case 8:
                result = R.drawable.avatar_128_status_8;
                break;

                default:
                    result = R.drawable.avatar_128_status_8;
                    break;
        }

        return result;
    }

}
