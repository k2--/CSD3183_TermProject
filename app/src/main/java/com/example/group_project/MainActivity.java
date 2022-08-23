package com.example.group_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private LinearLayout loginOptions, userOptions;
    private DatabaseHelper dbo = new DatabaseHelper(this);
    private final static int LOGIN_REQCODE = 1;
    private Integer userid = -1;
    private User curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginOptions = (LinearLayout) findViewById(R.id.loginOptions);
        userOptions = (LinearLayout) findViewById(R.id.userOptions);
    }
    @Override
    public void onResume(){
        super.onResume();
        loadPage();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == LOGIN_REQCODE) {
                if (data != null)
                userid = data.getIntExtra("userid", -1);
            }
        }
    }
    public void login(View v){
        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivityForResult(myIntent, LOGIN_REQCODE);
    }
    public void register(View v){
        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);
    }
    public void forgotPwd(View v){
        Intent myIntent = new Intent(this, ForgotPwdActivity.class);
        startActivity(myIntent);
    }
    public void loadPage(){
        if(userid == null || userid == -1){
            loginOptions.setVisibility(View.VISIBLE);
            userOptions.setVisibility(View.GONE);
        }else{
            if (curUser == null) {
                curUser = dbo.getUserByID(userid);
            }
            loginOptions.setVisibility(View.GONE);
            userOptions.setVisibility(View.VISIBLE);
        }
    }
    public void logOut(View v){
        userid = -1;
        curUser = null;
        loadPage();
        Toast toast = Toast.makeText(getApplicationContext(),"Successfully logged out",Toast.LENGTH_SHORT);
        toast.show();
    }
    public void srtQuiz(View v){
        Intent myIntent = new Intent(this, ViewQuizActivity.class);
        startActivity(myIntent);
    }
    public void crtQuiz(View v){
        Intent myIntent = new Intent(this, CreateQuizActivity.class);
        startActivity(myIntent);
    }
}