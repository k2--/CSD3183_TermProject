package com.example.group_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPwdActivity extends AppCompatActivity {
    private DatabaseHelper dbo = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);
    }

    public void changePwd(View v){
        Toast toast;
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPswrd = (EditText) findViewById(R.id.etPassword);
        EditText etSecurity = (EditText) findViewById(R.id.etSecurity);

        String email = etEmail.getText().toString().trim();
        String password = etPswrd.getText().toString();
        String security = etSecurity.getText().toString();
        if(email.isEmpty() || password.isEmpty() || security.isEmpty()){
            toast = Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            User user = dbo.validateUserSecurity(email, security);
            if(user == null){
                toast = Toast.makeText(getApplicationContext(),"User with security code doesnt exist",Toast.LENGTH_SHORT);
                toast.show();
            }else{
                user.setPassword(password);
                dbo.addUser(user);
                toast = Toast.makeText(getApplicationContext(),"User Password Successfully changed",Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }
    }
}