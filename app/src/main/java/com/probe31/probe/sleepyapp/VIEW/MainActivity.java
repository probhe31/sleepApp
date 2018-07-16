package com.probe31.probe.sleepyapp.VIEW;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.probe31.probe.sleepyapp.R;

public class MainActivity extends AppCompatActivity {

    Button mainButton;
    ImageView avatarImage;
    boolean isAwake = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainButton = (Button) findViewById(R.id.button_main_main);
        avatarImage = (ImageView) findViewById(R.id.image_main_avatar);

        switchButton(isAwake);
        //TODO: llamar al endPoint init
    }

    public void OnClickMainButton(View view)
    {
        isAwake=!isAwake;
        switchButton(isAwake);
    }

    void setStatusImage(int status)
    {
        switch (status)
        {
            case 1:
                avatarImage.setImageResource(R.drawable.avatar_512_status_1);
                break;
            case 2:
                avatarImage.setImageResource(R.drawable.avatar_512_status_2);
                break;
            case 3:
                avatarImage.setImageResource(R.drawable.avatar_512_status_3);
                break;
            case 4:
                avatarImage.setImageResource(R.drawable.avatar_512_status_4);
                break;
            case 5:
                avatarImage.setImageResource(R.drawable.avatar_512_status_5);
                break;
            case 6:
                avatarImage.setImageResource(R.drawable.avatar_512_status_6);
                break;
            case 7:
                avatarImage.setImageResource(R.drawable.avatar_512_status_7);
                break;
            case 8:
                avatarImage.setImageResource(R.drawable.avatar_512_status_8);
                break;
        }
    }

    void switchButton(boolean isAwake)
    {
        if(isAwake){

            mainButton.setText(getText(R.string.button_main_awake));
        }else{

            mainButton.setText(getText(R.string.button_main_sleep));
        }
    }
}
