package com.example.manageu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageu.Model.CurUser;



public class AccountFragment extends Fragment {

    TextView name ;
    TextView age ;
    TextView email ;
    TextView role;
    Button logout;
    private View view;
    Context context;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment(Context context) {
        this.context=context;
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
   /* public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TaskActivity.addTask.setVisibility(View.INVISIBLE);
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_account, container, false);
        name = view.findViewById(R.id.nameUser);
        age = view.findViewById(R.id.ageUser);
        email = view.findViewById(R.id.emailUser);
        role = view.findViewById(R.id.roleUser);
        logout=view.findViewById(R.id.logout);
        CurUser cur = CurUser.getInstance();
        name.setText("Name : "+cur.nam.toUpperCase());
        age.setText("Age : "+cur.age);
        email.setText("Email : "+cur.em);
        role.setText("Role : "+cur.rol);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskActivity.id_list.clear();
                TaskActivity.task_list.clear();
                TaskActivity.detail_list.clear();
                TaskActivity.time_list.clear();
                Intent intent=new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}