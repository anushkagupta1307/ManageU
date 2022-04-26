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

public class SleepFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.all_genre_frag,container, false);
        String[] items ={"Oceanus","Slow Water", "Sunset", "Water Drops", "Waves and Tears"};
        ListView lV = view.findViewById(R.id.list);
        int images[] = {R.drawable.peace, R.drawable.peace, R.drawable.peace, R.drawable.peace, R.drawable.peace};

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
                    i.putExtra("Song", "Oceanus");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Oceanus",Toast.LENGTH_SHORT).show();
                }
                else if(position == 1){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Slow Water");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Slow Water",Toast.LENGTH_SHORT).show();
                }
                else if(position == 2){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Sunset");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Sunset",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Water Drops");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Water Drops",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Waves and Tears");
                    startActivity(i);
                    Toast.makeText(v.getContext(),"Warm Memories Emotional Inspiring Piano",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}