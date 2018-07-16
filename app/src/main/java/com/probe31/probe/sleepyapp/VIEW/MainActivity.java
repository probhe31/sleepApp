package com.probe31.probe.sleepyapp.VIEW;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.probe31.probe.sleepyapp.MODEL.AwakeResponse;
import com.probe31.probe.sleepyapp.MODEL.InitResponse;
import com.probe31.probe.sleepyapp.MODEL.SleepResponse;
import com.probe31.probe.sleepyapp.R;
import com.probe31.probe.sleepyapp.VIEW_MODEL.MainActivityViewModel;



public class MainActivity extends AppCompatActivity {

    Button mainButton;
    ImageView avatarImage;
    TextView hoursText;
    boolean isAwake = true;

    AnimationDrawable animationDrawable;
    ConstraintLayout constraintLayout;

    MainActivityViewModel mainActivityViewModel;

    RelativeLayout progressLayout;

    int sleepStatusCode = 8;

    enum State
    {
        SLEEP,
        AWAKE
    }

    State state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.progressLayout = findViewById(R.id.main_loading_layout);
        this.progressLayout.setVisibility(View.VISIBLE);
        this.hoursText = findViewById(R.id.text_hour_sleep);
        mainButton = (Button) findViewById(R.id.button_main_main);
        avatarImage = (ImageView) findViewById(R.id.image_main_avatar);

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_main);




        SharedPreferences settings = getSharedPreferences("userData", 0);
        String token = settings.getString("token", "");

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getInitData(token).observe(this, new Observer<InitResponse>() {
            @Override
            public void onChanged(@Nullable InitResponse initResponse) {
                if(initResponse!=null)
                {
                    isAwake = initResponse.getStatus()!=8;
                    state = isAwake ? State.AWAKE : State.SLEEP;
                    switchBackground(state);
                    switchButton(state);
                    setStatusImage(initResponse.getStatus());
                    hoursText.setText(getText(R.string.main_you_sleep)+ " " + initResponse.getSleep_hour());


                    progressLayout.setVisibility(View.GONE);

                }
            }
        });
    }

    public void OnClickMainButton(View view)
    {

        SharedPreferences settings = getSharedPreferences("userData", 0);
        String token = settings.getString("token", "");

        isAwake=!isAwake;
        state = isAwake ? State.AWAKE : State.SLEEP;
        switchButton(state);
        switchBackground(state);

        switch (state) {
            case SLEEP:
                mainActivityViewModel.sleep(token).observe(this, new Observer<SleepResponse>() {
                    @Override
                    public void onChanged(@Nullable SleepResponse sleepResponse) {
                        if(sleepResponse!=null)
                        {
                            setStatusImage(sleepResponse.getStatus());
                        }
                    }
                });
                break;
            case AWAKE:
                mainActivityViewModel.awake(token).observe(this, new Observer<AwakeResponse>() {
                    @Override
                    public void onChanged(@Nullable AwakeResponse awakeResponse) {
                        if(awakeResponse!=null)
                        {
                            setStatusImage(awakeResponse.getStatus());
                        }
                    }
                });
                break;
        }






    }

    void setStatusImage(int status)
    {
        switch (status)
        {
            case 1:
                avatarImage.setImageResource(R.drawable.avatar_512_status_7);
                break;
            case 2:
                avatarImage.setImageResource(R.drawable.avatar_512_status_6);
                break;
            case 3:
                avatarImage.setImageResource(R.drawable.avatar_512_status_5);
                break;
            case 4:
                avatarImage.setImageResource(R.drawable.avatar_512_status_4);
                break;
            case 5:
                avatarImage.setImageResource(R.drawable.avatar_512_status_3);
                break;
            case 6:
                avatarImage.setImageResource(R.drawable.avatar_512_status_2);
                break;
            case 7:
                avatarImage.setImageResource(R.drawable.avatar_512_status_1);
                break;
            case 8:
                avatarImage.setImageResource(R.drawable.avatar_512_status_8);
                break;
        }
    }


    void switchBackground(State state)
    {
        switch (state) {
            case SLEEP:

                constraintLayout.setBackground(getDrawable(R.color.night_blue_top));
                /*constraintLayout.setBackground(getDrawable(R.drawable.background_animation_night));
                animationDrawable = (AnimationDrawable)constraintLayout.getBackground();
                animationDrawable.setEnterFadeDuration(10);
                animationDrawable.setExitFadeDuration(10);
                animationDrawable.setOneShot(true);
                animationDrawable.start();*/

                break;
            case AWAKE:

                constraintLayout.setBackground(getDrawable(R.color.day_top));
                /*constraintLayout.setBackground(getDrawable(R.drawable.background_animation_day));
                animationDrawable = (AnimationDrawable)constraintLayout.getBackground();
                animationDrawable.setEnterFadeDuration(10);
                animationDrawable.setExitFadeDuration(10);
                animationDrawable.setOneShot(true);
                animationDrawable.start();*/



                break;
        }
    }

    void switchButton(State state)
    {
        switch (state) {
            case SLEEP:
                mainButton.setText(getText(R.string.button_main_awake));
                break;
            case AWAKE:
                mainButton.setText(getText(R.string.button_main_sleep));
                break;
        }

    }
}
