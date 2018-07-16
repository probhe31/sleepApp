package com.probe31.probe.sleepyapp.VIEW;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.probe31.probe.sleepyapp.MODEL.RegisterResponse;
import com.probe31.probe.sleepyapp.MODEL.User;
import com.probe31.probe.sleepyapp.R;
import com.probe31.probe.sleepyapp.VIEW_MODEL.RegisterUserActivityViewModel;

public class RegisterActivity extends AppCompatActivity {

    RegisterUserActivityViewModel registerUserActivityVM;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.registerUserActivityVM = ViewModelProviders.of(this).get(RegisterUserActivityViewModel.class);
        this.context = this;
    }

    public void sendRegisterForm(View view)
    {
        EditText userNameEdit = (EditText) findViewById(R.id.text_register_username);
        EditText passwordEdit = (EditText) findViewById(R.id.text_register_password);
        EditText passwordAgainEdit = (EditText) findViewById(R.id.text_register_password_again);
        ToggleButton sexToggle = (ToggleButton) findViewById(R.id.toggle_register_sex);

        boolean cancel = false;
        View focusView = null;

        String username = userNameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String passwordAgain = passwordAgainEdit.getText().toString();
        String sex = sexToggle.isChecked()?"F":"M";

        if(!registerUserActivityVM.isUserNameCorrect(username)){

            userNameEdit.setError(getText(R.string.register_username_error));
            focusView = userNameEdit;
            cancel = true;
        }

        if(!TextUtils.equals(password, passwordAgain))
        {
            passwordAgainEdit.setError(getText(R.string.register_password_again_error));
            focusView = passwordAgainEdit;
            cancel = true;
        }

        if(!registerUserActivityVM.isPasswordCorrect(password)){

            passwordEdit.setError(getText(R.string.register_password_error));
            focusView = passwordEdit;
            cancel = true;
        }

        if(cancel){
            if(focusView!=null)
            focusView.requestFocus();
        }else{
            User user = new User();
            user.username = username;
            user.password = password;
            user.sex = sex;
            registerUserActivityVM.registerUser(user).observe(this, new Observer<RegisterResponse>() {
                @Override
                public void onChanged(@Nullable RegisterResponse response) {
                    if(response!=null)
                    {
                        goToLogin();
                    }
                }
            });
        }


    }

    void goToLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    public void onClickButtonCancel(View view)
    {
        goToLogin();
    }


}
