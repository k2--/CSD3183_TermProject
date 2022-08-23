package com.example.group_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseHelper dbo = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View v){
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
        }else if(dbo.isUser(email)){
            toast = Toast.makeText(getApplicationContext(),"User with email already exist",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            User newUser = new User(0,email,password,security);
            dbo.addUser(newUser);
            toast = Toast.makeText(getApplicationContext(),"User Successfully registered",Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }
    }
}