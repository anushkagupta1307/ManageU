package com.example.manageu.Music;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manageu.R;
public class Relax extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_genre_frag,container, false);
        String[] items ={"Mystique","Nature", "Order", "Showreel", "Space Chillout"};
        ListView lV = view.findViewById(R.id.list);
        int images[] = {R.drawable.relax, R.drawable.relax, R.drawable.relax, R.drawable.relax, R.drawable.relax};

        ArrayAdapter<String> lVA = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );
        MyAdapter adapter = new MyAdapter(view.getContext(), items, images);
        lV.setAdapter(adapter);
//        lV.setBackgroundResource(R.drawable.rounded_list);
//        lV.setAdapter(lVA);
        lV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> l, View v, int position, long id){

                if(position == 0){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Mystique");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Mystique",Toast.LENGTH_SHORT).show();
                }
                else if(position == 1){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Nature");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Nature",Toast.LENGTH_SHORT).show();
                }
                else if(position == 2){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Order");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Order",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Showreel");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Showreel",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Space Chillout");
                    startActivity(i);
                    Toast.makeText(v.getContext(),"Space Chillout",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}