package com.developerali.groundconnect.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.developerali.groundconnect.MainActivity;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            Animation lastZoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
            Animation zoomOut = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.zoom_out);

            zoomIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) { }

                @Override
                public void onAnimationEnd(Animation animation) {

                    binding.logo.startAnimation(zoomOut);
                }

                @Override
                public void onAnimationRepeat(Animation animation) { }
            });

            binding.logo.startAnimation(zoomIn);

            zoomOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    binding.logo.setAnimation(lastZoomIn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastZoomIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }



    }
}