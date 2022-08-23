package com.example.group_project;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizDetailFragment extends Fragment implements View.OnClickListener{
    TextView tvName,tvQst1,tvQst2,tvQst3,tvAns1,tvAns2,tvAns3, tvResult;
    LinearLayout lyQuiz,lyQst1,lyQst2,lyQst3;
    EditText etAns1,etAns2,etAns3;
    Button btnSubmit;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quizdetail, container, false);
        tvResult = view.findViewById(R.id.tvResult);
        tvName = view.findViewById(R.id.tvName);
        tvQst1 = view.findViewById(R.id.tvQst1);
        tvQst2 = view.findViewById(R.id.tvQst2);
        tvQst3 = view.findViewById(R.id.tvQst3);
        tvAns1 = view.findViewById(R.id.tvAns1);
        tvAns2 = view.findViewById(R.id.tvAns2);
        tvAns3 = view.findViewById(R.id.tvAns3);
        lyQuiz = view.findViewById(R.id.lyQuiz);
        lyQst1 = view.findViewById(R.id.lyQst1);
        lyQst2 = view.findViewById(R.id.lyQst2);
        lyQst3 = view.findViewById(R.id.lyQst3);
        etAns1 = view.findViewById(R.id.etAns1);
        etAns2 = view.findViewById(R.id.etAns2);
        etAns3 = view.findViewById(R.id.etAns3);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        lyQuiz.setVisibility(View.GONE);
        return view;
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        String ans1,ans2,ans3, userAns1, userAns2, userAns3;
        int correct = 0, questions = 0;
        ans1 = tvAns1.getText().toString();
        ans2 = tvAns2.getText().toString();
        ans3 = tvAns3.getText().toString();
        userAns1 = etAns1.getText().toString();
        userAns2 = etAns2.getText().toString();
        userAns3 = etAns3.getText().toString();
        int success_green = Color.parseColor("#4BB543");
        int failure_red = Color.parseColor("#FC100D");
        if(!ans1.isEmpty()){
            questions++;
            if(ans1.equals(userAns1)){
                correct++;
                lyQst1.setBackgroundColor(success_green);
            }else{
                lyQst1.setBackgroundColor(failure_red);
            }
        }
        if(!ans2.isEmpty()) {
            questions++;
            if (ans2.equals(userAns2)) {
                correct++;
                lyQst2.setBackgroundColor(success_green);
            } else {
                lyQst2.setBackgroundColor(failure_red);
            }
        }
        if(!ans3.isEmpty()) {
            questions++;
            if (ans3.equals(userAns3)) {
                correct++;
                lyQst3.setBackgroundColor(success_green);
            } else {
                lyQst3.setBackgroundColor(failure_red);
            }
        }
        tvResult.setText("You got "+correct+"/"+questions+ " Correct");
        etAns1.setEnabled(false);
        etAns2.setEnabled(false);
        etAns3.setEnabled(false);
        btnSubmit.setVisibility(View.GONE);
    }

    public void change(Quiz quiz){
        if(quiz != null) {
            lyQuiz.setVisibility(View.VISIBLE);
            tvName.setText(quiz.getName());
            tvQst1.setText(quiz.getQst_1());
            tvQst2.setText(quiz.getQst_2());
            tvQst3.setText(quiz.getQst_3());
            tvAns1.setText(quiz.getAns_1());
            tvAns2.setText(quiz.getAns_2());
            tvAns3.setText(quiz.getAns_3());

            etAns1.setEnabled(true);
            etAns2.setEnabled(true);
            etAns3.setEnabled(true);
            etAns1.setText("");
            etAns2.setText("");
            etAns3.setText("");
            tvResult.setText("");
            lyQst1.setBackgroundColor(Color.TRANSPARENT);
            lyQst2.setBackgroundColor(Color.TRANSPARENT);
            lyQst3.setBackgroundColor(Color.TRANSPARENT);
            if (quiz.getQst_2().isEmpty()) {
                lyQst2.setVisibility(View.GONE);
            }else{
                lyQst2.setVisibility(View.VISIBLE);
            }
            if (quiz.getQst_3().isEmpty()) {
                lyQst3.setVisibility(View.GONE);
            }else{
                lyQst3.setVisibility(View.VISIBLE);
            }
            tvAns2.setVisibility(View.GONE);
            tvAns3.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
        }else{
            lyQuiz.setVisibility(View.GONE);
        }
    }
}
