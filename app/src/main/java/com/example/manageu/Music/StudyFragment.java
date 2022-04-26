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

public class StudyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.all_genre_frag,container, false);
        String[] items ={"A Promise","Beautiful Piano", "Gentle Feelings", "Morning Light", "Warm Memories Emotional Inspiring Piano"};
        ListView lV = view.findViewById(R.id.list);
        int images[] = {R.drawable.study, R.drawable.study, R.drawable.study, R.drawable.study, R.drawable.study};

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
                    i.putExtra("Song", "A Promise");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"A Promise",Toast.LENGTH_SHORT).show();
                }
                else if(position == 1){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Beautiful Piano");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Beautiful Piano",Toast.LENGTH_SHORT).show();
                }
                else if(position == 2){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Gentle Feelings");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Gentle Feelings",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3){
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Morning Light");
                    startActivity(i);

                    Toast.makeText(v.getContext(),"Morning Light",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(v.getContext(),MusicActivity.class);
                    i.putExtra("Song", "Warm Memories Emotional Inspiring Piano");
                    startActivity(i);
                    Toast.makeText(v.getContext(),"Warm Memories Emotional Inspiring Piano",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}

class MyAdapter extends ArrayAdapter<String>{
    Context context;
    String rTitle[];
    int rImgs[];

    MyAdapter(Context c, String title[], int imgs[]){
        super(c, R.layout.row,R.id.tv1, title);
        this.context =c;
        this.rTitle = title;
        this.rImgs = imgs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row,parent, false);
        ImageView images = row.findViewById(R.id.img);

        images.setImageResource(rImgs[position]);
        TextView myTitle = row.findViewById(R.id.tv1);

        myTitle.setText(rTitle[position]);
        return row;
    }
}