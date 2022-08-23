package com.example.group_project;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class QuizListFragment extends ListFragment {
    DatabaseHelper dbo;
    ArrayList<Quiz> quizzes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_quizlist, container, false);
        dbo= new DatabaseHelper(getContext());
        quizzes = dbo.getAllQuizzes();
        QuizViewAdaptor adapter = new QuizViewAdaptor(getContext(), quizzes);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(position>=0) {
            QuizDetailFragment detailsFragment = (QuizDetailFragment) getFragmentManager().findFragmentById(R.id.QuizDetailFragment);
            detailsFragment.change(quizzes.get(position));
            getListView().setSelector(android.R.color.holo_blue_dark);
        }
    }
}