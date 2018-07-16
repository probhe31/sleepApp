package com.probe31.probe.sleepyapp;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.probe31.probe.sleepyapp.MODEL.TokenResponse;
import com.probe31.probe.sleepyapp.VIEW_MODEL.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel loginActivityVM;
    RelativeLayout loginProgressLayout;
    LinearLayout loginFormLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.loginProgressLayout = findViewById(R.id.login_loading_layout);
        this.loginFormLayout = findViewById(R.id.login_form_layout);
        this.loginActivityVM = ViewModelProviders.of(this).get(LoginActivityViewModel.class);
        this.context = this;

        loginActivityVM.checkNetwork().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer idError) {
                if(idError==401){
                    Toast.makeText(context, "Authorization problem", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void sendLoginForm(View view) {

        EditText userNameEdit = (EditText) findViewById(R.id.text_login_username);
        EditText passwordEdit = (EditText) findViewById(R.id.text_login_password);
        userNameEdit.setError(null);
        passwordEdit.setError(null);
        boolean cancel = false;
        View focusView = null;

        String username = userNameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        if(!loginActivityVM.validateUsername(username)){
            userNameEdit.setError(getText(R.string.login_username_error));
            focusView = userNameEdit;
            cancel = true;
        }

        if(!loginActivityVM.validatePassword(password)){
            passwordEdit.setError(getText(R.string.login_password_error));
            focusView = passwordEdit;
            cancel = true;
        }

        if (cancel) {
            if(focusView!=null)
                focusView.requestFocus();
        }else{
            loginActivityVM.tryLogin(username, password).observe(this, new Observer<TokenResponse>() {
                @Override
                public void onChanged(@Nullable TokenResponse tokenResponse) {
                    if(tokenResponse!=null){
                        SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
                        preferences.edit().putString("token", tokenResponse.getToken()).commit();
                        goToMainMenu();
                    }else{
                        showProgress(false);
                    }
                }
            });

            hideKeyboard(this);
            showProgress(true);
        }
    }


    public static void hideKeyboard(Activity activity) {

        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void showProgress(boolean show){

        if(show){
            loginFormLayout.setVisibility(View.GONE);
            loginProgressLayout.setVisibility(View.VISIBLE);
        }else{
            loginFormLayout.setVisibility(View.VISIBLE);
            loginProgressLayout.setVisibility(View.GONE);
        }
    }

    void goToMainMenu(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToRegister(View view){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
