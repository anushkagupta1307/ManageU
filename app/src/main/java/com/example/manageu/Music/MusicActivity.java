package com.example.manageu.Music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.manageu.R;

import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    Button start, pause, next, prev;
    SeekBar seekbar;
    MediaPlayer mediPlay;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        start = findViewById(R.id.playBtn);
        next = findViewById(R.id.nxtBtn);
        prev = findViewById(R.id.prevBut);
        seekbar = findViewById(R.id.seek_bar);
        tv = findViewById(R.id.textView6);
        Intent intent = getIntent();
        String song = intent.getStringExtra("Song");
        tv.setText(song);


        //// Study
        if(song.equals("A Promise"))
            mediPlay = MediaPlayer.create(this, R.raw.a_promise);
        else if(song.equals("Beautiful Piano"))
            mediPlay = MediaPlayer.create(this, R.raw.beautiful_piano);
        else if(song.equals("Gentle Feelings"))
            mediPlay = MediaPlayer.create(this, R.raw.gentle_feelings);
        else if(song.equals("Morning Light"))
            mediPlay = MediaPlayer.create(this, R.raw.morning_light);
        else if(song.equals("Warm Memories Emotional Inspiring Piano"))
            mediPlay = MediaPlayer.create(this, R.raw.warm_memories_emotional_inspiring_piano);

            //// Relax
        else if(song.equals("Mystique"))
            mediPlay = MediaPlayer.create(this, R.raw.mystique);
        else if(song.equals("Nature"))
            mediPlay = MediaPlayer.create(this, R.raw.nature);
        else if(song.equals("Order"))
            mediPlay = MediaPlayer.create(this, R.raw.order);
        else if(song.equals("Showreel"))
            mediPlay = MediaPlayer.create(this, R.raw.showreel);
        else if(song.equals("Space Chillout"))
            mediPlay = MediaPlayer.create(this, R.raw.space_chillout);

            //// Peace
        else if(song.equals("Oceanus"))
            mediPlay = MediaPlayer.create(this, R.raw.oceanus);
        else if(song.equals("Slow Water"))
            mediPlay = MediaPlayer.create(this, R.raw.slow_water);
        else if(song.equals("Sunset"))
            mediPlay = MediaPlayer.create(this, R.raw.sunset);
        else if(song.equals("Water Drops"))
            mediPlay = MediaPlayer.create(this, R.raw.water_drops);
        else if(song.equals("Waves and Tears"))
            mediPlay = MediaPlayer.create(this, R.raw.waves_and_tears);

        else
            mediPlay = MediaPlayer.create(this, R.raw.kick);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!mediPlay.isPlaying()){
                    mediPlay.start();
                    Toast.makeText(MusicActivity.this, "Play", Toast.LENGTH_SHORT).show();
                    start.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                }
                else{
                    mediPlay.pause();
                    Toast.makeText(MusicActivity.this, "Pause", Toast.LENGTH_SHORT).show();
                    start.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }
            }
        });

        seekbar.setMax(mediPlay.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekbar.setProgress(mediPlay.getCurrentPosition());
                if(mediPlay.getDuration() <= mediPlay.getCurrentPosition()){
                    start.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }
            }
        },0,900);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediPlay.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mediPlay.isPlaying()){
            mediPlay.stop();
            Toast.makeText(MusicActivity.this, "Music Stopped", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}