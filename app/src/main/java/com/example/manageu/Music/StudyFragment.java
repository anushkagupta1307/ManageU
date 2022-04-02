package com.example.manageu.Music;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.manageu.R;

public class StudyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.all_genre_frag,container, false);
        String[] items ={"Heart Beat","Kick Buttowski"};
        ListView lV = view.findViewById(R.id.list);

        ArrayAdapter<String> lVA = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );
        lV.setAdapter(lVA);
        lV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> l, View v, int position, long id){

                if(position == 0){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "heart");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Heart",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "kick");
                    startActivity(i);
                    Toast.makeText(v.getContext(),"Beat",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
