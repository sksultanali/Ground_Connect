package com.developerali.groundconnect.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.developerali.groundconnect.databinding.ActivityPostBinding;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }
}