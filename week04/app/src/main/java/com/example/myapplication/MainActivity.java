package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Button btn;
    private AssetFileDescriptor descriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = getAssets();
        try {
            descriptor = am.openFd("just_dance.mp3");
        } catch (IOException e) {
            Log.e(TAG, "IOException : " + e.toString());
            e.printStackTrace();
        }
        Button btPlayer = (Button) findViewById(R.id.buttonPlay);
        Button btStop = (Button) findViewById((R.id.buttonStop));
        btPlayer.setOnClickListener(this);
        btStop.setOnClickListener(this);
//        btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        if(v.getId() == R.id.buttonPlay) {
            try {
                mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                        descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mediaPlayer.stop();
        }
    }

//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent(Intent.ACTION_VIEW,
//                Uri.parse("http://www.inha.ac.kr"));
//        startActivity(intent);
//    }
}