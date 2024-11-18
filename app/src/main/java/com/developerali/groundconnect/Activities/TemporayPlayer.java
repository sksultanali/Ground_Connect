package com.developerali.groundconnect.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.developerali.groundconnect.databinding.ActivityTemporayPlayerBinding;

public class TemporayPlayer extends AppCompatActivity {

    ActivityTemporayPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTemporayPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        String UserNameUT = intent.getStringExtra("userNameUT");
        String DesUT = intent.getStringExtra("decUT");
        String VideoUrlUT = intent.getStringExtra("videoUrlUT");



        if (UserNameUT != null){
            binding.textVideoTitle.setText(UserNameUT);
        }else {
            binding.textVideoTitle.setText("UserName");
        }
        if (DesUT != null){
            binding.textVideoDescription.setText(DesUT);
        }else {
            binding.textVideoDescription.setText("This have no description");
        }


        binding.videoView.setVideoPath(VideoUrlUT);
        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                binding.videoProgressBar.setVisibility(View.GONE);
                mediaPlayer.start();
            }
        });

        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });



    }
}