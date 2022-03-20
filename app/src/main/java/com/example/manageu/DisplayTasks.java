package com.example.manageu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manageu.utils.RecyclerSpacingDecorator;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayTasks extends Fragment {

    RecyclerView recycler_view;
    Context context;

    public DisplayTasks(Context context) {
        this.context = context;
    }

    public DisplayTasks()
    {

    }

    public static DisplayTasks newInstance(String param1, String param2) {
        DisplayTasks fragment = new DisplayTasks();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TaskActivity.addTask.setVisibility(View.VISIBLE);
        View view = inflater.inflate(R.layout.fragment_display_tasks, container, false);
        recycler_view = view.findViewById(R.id.recycler_view);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),TaskActivity.task_list,TaskActivity.detail_list,TaskActivity.time_list, TaskActivity.id_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_view.setLayoutManager(linearLayoutManager);
        RecyclerSpacingDecorator decorator = new RecyclerSpacingDecorator(50);
        recycler_view.addItemDecoration(decorator);
        recycler_view.setAdapter(recyclerAdapter);
        return view;
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}