package com.example.manageu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manageu.utils.RecyclerSpacingDecorator;

import java.util.ArrayList;
import java.util.Arrays;

public class DisplayTasks extends Fragment {

    RecyclerView recycler_view;

    ArrayList<String> task_list = new ArrayList(Arrays.asList("Study", "Exercise","Photography","Music","Dance"));
    ArrayList<String> detail_list = new ArrayList(Arrays.asList("Mobile Computing Lec2","Cardio","Bird Photography","Play a song","Zoomba"));
    ArrayList<String> time_list = new ArrayList(Arrays.asList("2 Hours","45 Minutes","1 Hour","1 Hour","30 Minutes"));

    /*public DisplayTasks() {
        // Required empty public constructor
    }*/

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
        View view = inflater.inflate(R.layout.fragment_display_tasks, container, false);
        recycler_view = view.findViewById(R.id.recycler_view);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(),task_list,detail_list,time_list);
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