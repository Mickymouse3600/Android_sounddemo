package com.example.mickymouse.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp3;
    AudioManager Audio;


    public void PlayAudio(View view){

        mp3.start();

    }
    public void PauseAudio(View view){

        mp3.pause();

    }
    public void StopAudio(View view){


        mp3.stop();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mp3 = MediaPlayer.create(this, R.raw.carsounds);

        Audio=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int Mavolume= Audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int CurVolume=Audio.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar VolumeCtrl= (SeekBar)findViewById(R.id.seekBar);
        VolumeCtrl.setMax(Mavolume);
         VolumeCtrl.setProgress(CurVolume);
        VolumeCtrl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){

                Log.i("Seek Value", Integer.toString(progress));
                Audio.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }


        });



        final SeekBar Scuber= (SeekBar)findViewById(R.id.scruber);
        Scuber.setMax(mp3.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                Scuber.setProgress(mp3.getCurrentPosition());

            }
        },0,1000);

        Scuber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                mp3.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }
}

