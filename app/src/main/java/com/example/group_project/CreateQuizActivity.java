package com.example.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateQuizActivity  extends AppCompatActivity {
    DatabaseHelper dbo = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createquiz);
    }

    public void createQuiz(View v) {
        Toast toast;
        EditText etName = (EditText) findViewById(R.id.etName);
        EditText etQst1 = (EditText) findViewById(R.id.etQst1);
        EditText etQst2 = (EditText) findViewById(R.id.etQst2);
        EditText etQst3 = (EditText) findViewById(R.id.etQst3);
        EditText etAns1 = (EditText) findViewById(R.id.etAns1);
        EditText etAns2 = (EditText) findViewById(R.id.etAns2);
        EditText etAns3 = (EditText) findViewById(R.id.etAns3);

        String name = etName.getText().toString();
        String qst1 = etQst1.getText().toString();
        String qst2 = etQst2.getText().toString();
        String qst3 = etQst3.getText().toString();
        String ans1 = etAns1.getText().toString();
        String ans2 = etAns2.getText().toString();
        String ans3 = etAns3.getText().toString();
        String errorMsg =null;

        if (name.isEmpty()){
            errorMsg = "Please enter a quiz name";
        }else if (qst1.isEmpty() || ans1.isEmpty()){
            errorMsg = "Question 1 & Answer 1 are mandatory please enter";
        }else if ((!qst2.isEmpty() && ans2.isEmpty()) || (qst2.isEmpty() && !ans2.isEmpty())){
            errorMsg =  "You started to fill Question 2, please clear or complete";
        }else if ((!qst3.isEmpty() && ans3.isEmpty()) || (qst3.isEmpty() && !ans3.isEmpty())){
            errorMsg = "You started to fill Question 3, please clear or complete";
        }else if (!qst3.isEmpty() && qst2.isEmpty()){
            errorMsg = "Question 2 cannot be empty if you filled Question 3";
        }

        if(errorMsg != null){
            toast = Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Quiz newQuiz = new Quiz(0,name,qst1,qst2,qst3,ans1,ans2,ans3);
            dbo.addQuiz(newQuiz);
            toast = Toast.makeText(getApplicationContext(),"Quiz Created ",Toast.LENGTH_SHORT);
            toast.show();
            this.finish();
        }
    }
}
