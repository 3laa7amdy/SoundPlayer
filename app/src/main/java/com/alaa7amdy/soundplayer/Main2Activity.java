package com.alaa7amdy.soundplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    static MediaPlayer mediaPlayer;
    Button btnPlay, btnStop, btnPause;
    int pauseCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name =  bundle.getString("name");
        String id = bundle.getString("id");
        Log.d("id msss", String.valueOf(id));
        Toast.makeText(this, "id " + id, Toast.LENGTH_SHORT).show();
        TextView textView = findViewById(R.id.name);
        textView.setText(name);

        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            //mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(getApplicationContext(),Integer.parseInt(id) );
        mediaPlayer.start();

        btnPlay = findViewById(R.id.btn_play);
        btnStop = findViewById(R.id.btn_stop);
        btnPause = findViewById(R.id.btn_pause);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.m1);
                    mediaPlayer.start();
                }else if ( !mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(pauseCurrentPosition);
                    mediaPlayer.start();
                }


            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer = null;
                }

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null){
                    mediaPlayer.pause();
                    pauseCurrentPosition = mediaPlayer.getCurrentPosition();
                }

            }
        });
    }


}


