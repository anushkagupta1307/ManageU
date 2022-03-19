package com.example.manageu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private ArrayList<String> task_list;
    private ArrayList<String> detail_list;
    private ArrayList<String> time_list;
    String task;
    String task_detail;
    String time;
    private String TAG = RecyclerAdapter.class.getSimpleName();

    BroadcastReceiver brocRec;
    public RecyclerAdapter(Context context, ArrayList task_list,ArrayList detail_list,ArrayList time_list) {
        this.context = context;
        this.task_list = task_list;
        this.detail_list = detail_list;
        this.time_list = time_list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks,parent,false);
        viewHolderClass holder = new viewHolderClass(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        viewHolderClass holderclass = (viewHolderClass)holder;
        holderclass.task.setText(task_list.get(position));
        holderclass.task_detail.setText(detail_list.get(position));
        holderclass.time.setText(time_list.get(position));
        holderclass.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Toast.makeText(context.getApplicationContext(), "Delete pressed", Toast.LENGTH_SHORT).show();
            }
        });

        holderclass.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Toast.makeText(context.getApplicationContext(), "Done pressed", Toast.LENGTH_SHORT).show();
                holderclass.done.setVisibility(View.GONE);
                holderclass.delete.setVisibility(View.GONE);
                holderclass.taskdone.setVisibility(View.VISIBLE);
                holderclass.taskdone.setText("Task Completed");
            }
        });

    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }

    public class viewHolderClass extends RecyclerView.ViewHolder
    {
        TextView task,task_detail,time,taskdone;
        Button delete,done;
        public viewHolderClass(@NonNull View itemView) {
            super(itemView);
            task = (TextView) itemView.findViewById(R.id.taskView);
            task_detail = (TextView) itemView.findViewById(R.id.detailView);
            time = (TextView) itemView.findViewById(R.id.timeView);
            delete = (Button) itemView.findViewById(R.id.button5);
            done = (Button) itemView.findViewById(R.id.button6);
            taskdone = (TextView) itemView.findViewById(R.id.taskdoneview);
            taskdone.setVisibility(View.INVISIBLE);

        }
    }


}

