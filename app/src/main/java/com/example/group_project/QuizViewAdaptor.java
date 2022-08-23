package com.example.group_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuizViewAdaptor extends ArrayAdapter<Quiz> {
    public QuizViewAdaptor(@NonNull Context context, ArrayList<Quiz> records) {
        super(context, 0, records);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listview_cust_quiz, parent, false);
        }

        Quiz curQuiz = getItem(position);

        TextView tvQuizName = currentItemView.findViewById(R.id.tvQuizName);
        tvQuizName.setText(curQuiz.getName());
        TextView tvQuizID = currentItemView.findViewById(R.id.tvQuizID);
        tvQuizID.setText(""+curQuiz.getId());

        return currentItemView;
    }
}
