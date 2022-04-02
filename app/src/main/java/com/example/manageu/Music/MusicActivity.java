package com.example.manageu.Music;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.manageu.R;

import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    Button start, pause, next, prev;
    SeekBar seekbar;
    MediaPlayer mediPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        start = findViewById(R.id.playBtn);
        next = findViewById(R.id.nxtBtn);
        prev = findViewById(R.id.prevBut);
        seekbar = findViewById(R.id.seek_bar);
        Intent intent = getIntent();
        String song = intent.getStringExtra("Song");

        if(song.equals("heart")){
            mediPlay = MediaPlayer.create(this, R.raw.heart);
            Toast.makeText(MusicActivity.this, "iff", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(MusicActivity.this, "elsse", Toast.LENGTH_SHORT).show();
            mediPlay = MediaPlayer.create(this, R.raw.kick);
        }

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