package com.example.manageu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.manageu.Dao.FetchStatsListDbAccess;
import com.example.manageu.Dao.UserProfileAccess;
import com.example.manageu.Model.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class recommender extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner recommender;
    ImageView thumb1,thumb2,thumb3;

    private String getYouTubeId (String youTubeUrl) {
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return matcher.group();
        } else {
            return "error";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommender);

        //binding=ActivityTaskBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        //String thumbnail="https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=HIj8wU_rGIU")+"/0.jpg";

        thumb1=(ImageView) findViewById(R.id.imageView2);
        thumb2=(ImageView)findViewById(R.id.imageView3);
        thumb3=(ImageView)findViewById(R.id.imageView4);

//        thumb1.setVisibility(View.INVISIBLE);
//        thumb2.setVisibility(View.INVISIBLE);
//        thumb3.setVisibility(View.INVISIBLE);

//        Glide.with(recommender.this).load(thumbnail).into(thumb1);
//        Glide.with(recommender.this).load(thumbnail).into(thumb2);
//        Glide.with(recommender.this).load(thumbnail).into(thumb3);


        recommender=findViewById(R.id.spinner_recommender);

        User loggedUser= UserProfileAccess.loggedInUser;
        FetchStatsListDbAccess fetchStatsListDbAccess=new FetchStatsListDbAccess(com.example.manageu.recommender.this);
        fetchStatsListDbAccess.execute();




        if (loggedUser.role.equals("Student"))
        {

            String[] items = new String[]{"","Study", "Sports", "Netflix","Exercise","Hobby"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinneractivtityfortask, items);
            recommender.setAdapter(adapter);
            //selected =recommender.getSelectedItem().toString();

        }
        else if(loggedUser.role.equals("Working Professional"))
        {
            //recommender=findViewById(R.id.spinner_recommender);
            String[] items = new String[]{"","Work","Meetings","Sports","Exercise","Netflix","Hobby"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinneractivtityfortask, items);
            recommender.setAdapter(adapter);
            //selected =recommender.getSelectedItem().toString();
        }
        else{
            //recommender=findViewById(R.id.spinner_recommender);
            String[] items = new String[]{"","Cooking", "Cleaning", "Exercise","Netflix","Hobby"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinneractivtityfortask, items);
            recommender.setAdapter(adapter);
            //selected =
        }

        recommender.setOnItemSelectedListener( recommender.this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        User loggedUser= UserProfileAccess.loggedInUser;
        FetchStatsListDbAccess fetchStatsListDbAccess=new FetchStatsListDbAccess(com.example.manageu.recommender.this);
        fetchStatsListDbAccess.execute();

        String selected = recommender.getSelectedItem().toString();
        if (loggedUser.role.equals("Student")) {



            if (selected.equals("Study")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1ex_bNIFR1A&t=1573s") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=CMlSR2yK3Mc") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=iONDebHX9qk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1ex_bNIFR1A&t=1573s"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=CMlSR2yK3Mc"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=iONDebHX9qk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Sports")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=C62wTqPa8CM") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=vL0yww9nk9o") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1rfrfSU8GWc") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=C62wTqPa8CM"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vL0yww9nk9o"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1rfrfSU8GWc"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Netflix")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=-FrqlHlUgz4") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=6DxjJzmYsXo") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=pyM3z73oMAk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);
                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-FrqlHlUgz4"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6DxjJzmYsXo"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=pyM3z73oMAk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Exercise")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Hobby")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTech") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTec"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            }
        } else if (loggedUser.role.equals("Working Professional")) {
            //String selected = recommender.getSelectedItem().toString();
            if (selected.equals("Work")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=vDYP6AKw8bk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=4h04mCYvj3Q") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=tQSKyvjsUuI") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);
                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vDYP6AKw8bk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4h04mCYvj3Q"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=tQSKyvjsUuI"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Meetings")) {

                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=Xp0VAutGFyg&ab_channel=AdrianaGirdler") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=LWz57CpcSnE&ab_channel=JeffSu") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=ypxH_2qdePc&ab_channel=TheDistilledMan") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Xp0VAutGFyg&ab_channel=AdrianaGirdler"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=LWz57CpcSnE&ab_channel=JeffSu"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ypxH_2qdePc&ab_channel=TheDistilledMan"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Sports")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=C62wTqPa8CM") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=vL0yww9nk9o") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1rfrfSU8GWc") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=C62wTqPa8CM"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vL0yww9nk9o"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1rfrfSU8GWc"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Exercise")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Netflix")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=-FrqlHlUgz4") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=6DxjJzmYsXo") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=pyM3z73oMAk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);
                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-FrqlHlUgz4"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6DxjJzmYsXo"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=pyM3z73oMAk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Hobby")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTech") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTec"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            }
        } else {
            //String selected = recommender.getSelectedItem().toString();
            if (selected.equals("Cooking")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=UlKkQ9qnmzY&ab_channel=CakeLovers") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=XsipAaImDVc&ab_channel=Kanak%27sKitchen") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=10TTM1NaLPA&ab_channel=FOODCOUTUREbyChetnaPatel") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=UlKkQ9qnmzY&ab_channel=CakeLovers"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=XsipAaImDVc&ab_channel=Kanak%27sKitchen"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=10TTM1NaLPA&ab_channel=FOODCOUTUREbyChetnaPatel"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Cleaning")) {

                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=z-fcAQt851s&ab_channel=ThisCrazyLife") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=oz4mC8HXakE&ab_channel=5-MinuteCrafts") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=16t56ecMtQs&ab_channel=DailyFails") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=z-fcAQt851s&ab_channel=ThisCrazyLife"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=oz4mC8HXakE&ab_channel=5-MinuteCrafts"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=16t56ecMtQs&ab_channel=DailyFails"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Exercise")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1piFN_ioMVI&ab_channel=BumrungradInternationalHospital"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=digpucxFbMo&ab_channel=cultfitOfficial"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI&ab_channel=SELF"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            } else if (selected.equals("Netflix")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=-FrqlHlUgz4") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=6DxjJzmYsXo") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=pyM3z73oMAk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);
                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=-FrqlHlUgz4"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=6DxjJzmYsXo"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=pyM3z73oMAk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });


            } else if (selected.equals("Hobby")) {
                String thumbnail1 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail1).into(thumb1);
                String thumbnail2 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail2).into(thumb2);
                String thumbnail3 = "https://img.youtube.com/vi/" + getYouTubeId("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTech") + "/0.jpg";
                Glide.with(recommender.this).load(thumbnail3).into(thumb3);

                thumb1.setVisibility(View.VISIBLE);
                thumb2.setVisibility(View.VISIBLE);
                thumb3.setVisibility(View.VISIBLE);

                thumb1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_ae2j9jZY_U&ab_channel=CLASHBOY"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xJ5ct84-nmw&ab_channel=BFunk"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

                thumb3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=79hXV-0KD10&ab_channel=5-MinuteCraftsTec"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setPackage("com.google.android.youtube");
                        startActivity(intent);
                    }
                });

            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}