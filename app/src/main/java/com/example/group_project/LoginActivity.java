package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity  extends AppCompatActivity {
    private DatabaseHelper dbo = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        Toast toast;
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etPswrd = (EditText) findViewById(R.id.etPassword);

        String email = etEmail.getText().toString();
        String password = etPswrd.getText().toString();
        User user = dbo.validateUser(email,password);
        if (user != null){
            Intent intent = new Intent();
            intent.putExtra("userid", user.id);
            setResult(RESULT_OK, intent);
            toast = Toast.makeText(getApplicationContext(),"Welcome ",Toast.LENGTH_SHORT);
            toast.show();
            this.finish();
        }else {
            toast = Toast.makeText(getApplicationContext(),"Username or Password Incorrect",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void forgotPwd(View v){
        Intent myIntent = new Intent(this, ForgotPwdActivity.class);
        startActivity(myIntent);
    }
}
