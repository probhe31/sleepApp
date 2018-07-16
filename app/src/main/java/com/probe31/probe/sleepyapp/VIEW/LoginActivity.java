package com.probe31.probe.sleepyapp.VIEW;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.probe31.probe.sleepyapp.MODEL.TokenResponse;
import com.probe31.probe.sleepyapp.R;
import com.probe31.probe.sleepyapp.VIEW_MODEL.LoginActivityViewModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    LoginActivityViewModel loginActivityVM;
    RelativeLayout loginProgressLayout;
    LinearLayout loginFormLayout;

    Context context;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.constraintLayout = (ConstraintLayout) findViewById(R.id.constraint_login_layout);
        this.loginProgressLayout = findViewById(R.id.login_loading_layout);
        this.loginFormLayout = findViewById(R.id.login_form_layout);
        this.loginActivityVM = ViewModelProviders.of(this).get(LoginActivityViewModel.class);
        this.context = this;


        SharedPreferences settings = getSharedPreferences("userData", 0);
        int lastState = settings.getInt("last_state", 0);

        if(lastState==0)
        {
            constraintLayout.setBackground(getDrawable(R.color.day_top));
        }else if(lastState==1)
        {
            constraintLayout.setBackground(getDrawable(R.color.night_blue_top));
        }





        String token = settings.getString("token", "");

        if(TextUtils.isEmpty(token)){
            loginActivityVM.checkNetwork().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(@Nullable Integer idError) {
                    if(idError==401){
                        Toast.makeText(context, "Authorization problem", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //printhashkey();
        }else{
            goToMainMenu();
        }

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



    public void printhashkey(){

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.probe31.probe.sleepyapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

    }
}
