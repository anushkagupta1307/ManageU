package com.example.manageu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.example.manageu.Dao.DoneTaskDbAccess;
import com.example.manageu.Dao.FetchDoneTaskIdsDbAccess;
import com.example.manageu.Model.DoneTask;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private ArrayList<String> task_list;
    private ArrayList<String> detail_list;
    private ArrayList<String> time_list;
    private ArrayList<Integer> id_list;
    public static ArrayList<Integer> deleted_list=new ArrayList<>();
    String task;
    String task_detail;
    String time;
    private String TAG = RecyclerAdapter.class.getSimpleName();

    BroadcastReceiver brocRec;
    public RecyclerAdapter(Context context, ArrayList task_list,ArrayList detail_list,ArrayList time_list, ArrayList id_list) {
        this.context = context;
        this.task_list = task_list;
        this.detail_list = detail_list;
        this.time_list = time_list;
        this.id_list= id_list;
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

               activity.getContentResolver().delete(CalendarContract.Events.CONTENT_URI, CalendarContract.Events.TITLE+"=?", new String[]{task_list.get(holder.getAdapterPosition())});
            //    System.out.println("id to be deleted");
              //  System.out.println(id_list.get(holder.getAdapterPosition()));
              //  deleted_list.add(id_list.get(holder.getAdapterPosition()));
                TaskActivity.id_list.clear();
                TaskActivity.task_list.clear();
                TaskActivity.detail_list.clear();
                TaskActivity.time_list.clear();
                Intent i= new Intent(context, TaskActivity.class);
                context.startActivity(i);
            }
        });

        FetchDoneTaskIdsDbAccess fetchDoneTaskIdsDbAccess =new FetchDoneTaskIdsDbAccess(context.getApplicationContext());
        fetchDoneTaskIdsDbAccess.execute();
        try {
            Thread.sleep(100);
        }
        catch (Exception e){
        }
       if(FetchDoneTaskIdsDbAccess.doneTaskList.contains(id_list.get(holder.getAdapterPosition()))){
           holderclass.done.setVisibility(View.GONE);
           holderclass.delete.setVisibility(View.GONE);
           holderclass.taskdone.setVisibility(View.VISIBLE);
           holderclass.taskdone.setText("Task Completed");
       }else {
           holderclass.done.setVisibility(View.VISIBLE);
           holderclass.done.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   AppCompatActivity activity = (AppCompatActivity) view.getContext();
                   Toast.makeText(context.getApplicationContext(), "Done pressed", Toast.LENGTH_SHORT).show();
                   holderclass.done.setVisibility(View.GONE);
                   holderclass.delete.setVisibility(View.GONE);
                   holderclass.taskdone.setVisibility(View.VISIBLE);
                   holderclass.taskdone.setText("Task Completed");

                   DoneTask doneTask = new DoneTask();
                   doneTask.id = id_list.get(holder.getAdapterPosition());
                   doneTask.user_email = LoginPage.loggedInUserEmail;
                   doneTask.done = 1;
                   doneTask.time=time_list.get(holder.getAdapterPosition());
                   doneTask.title=task_list.get(holder.getAdapterPosition());
                   DoneTaskDbAccess doneTaskDbAccess = new DoneTaskDbAccess(context.getApplicationContext(), doneTask);
                   doneTaskDbAccess.execute();
               }
           });
       }

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

