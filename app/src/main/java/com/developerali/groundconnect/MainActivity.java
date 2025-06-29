package com.developerali.groundconnect;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.developerali.groundconnect.Activities.PostActivity;
import com.developerali.groundconnect.ApiRes.ApiService;
import com.developerali.groundconnect.BottomBars.DonationFragment;
import com.developerali.groundconnect.BottomBars.HomeFragment;
import com.developerali.groundconnect.BottomBars.SettingsFragment;
import com.developerali.groundconnect.BottomBars.ShortsFragment;
import com.developerali.groundconnect.Helpers.AccessToken;
import com.developerali.groundconnect.Models.NotificationRequest;
import com.developerali.groundconnect.databinding.ActivityMainBinding;
import com.developerali.groundconnect.databinding.DialogMessageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String BASE_URL = "https://fcm.googleapis.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).addToBackStack(null);
        transaction.commit();

        //eta bottom bar er click er jonno kaj kora
        //binding.bottomBar.setItemIconTintList(null);
        binding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                int id = item.getItemId();
                if (id == R.id.homeMenu) {
                    transaction.replace(R.id.content, new HomeFragment()).addToBackStack(null);
                }else if (id == R.id.shorts) {
                    transaction.replace(R.id.content, new ShortsFragment()).addToBackStack(null);
                }else if (id == R.id.donation){
                    transaction.replace(R.id.content, new DonationFragment()).addToBackStack(null);
                }else {
                    transaction.replace(R.id.content, new SettingsFragment()).addToBackStack(null);
                }
                transaction.commit();
                return true;
            }
        });

        binding.helpBtn.setOnClickListener(v->{
            showHelpDialog();
        });

        binding.addBtn.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        });

        //subscribing all messages
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/users")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                    }
                });
        checkNotification();
        //sendNotification();








    }

    private void sendNotification() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API service
        ApiService apiService = retrofit.create(ApiService.class);

        // Prepare the notification request
        NotificationRequest notificationRequest = new NotificationRequest(
                new NotificationRequest.Message(
                        "users",
                        new NotificationRequest.Notification(
                                "Ground Connect Success",
                                "This is from your candidate..."
                        )
                )
        );

        AccessToken accessTokenHelper = new AccessToken();
        accessTokenHelper.getAccessToken(new AccessToken.AccessTokenCallback() {
            @Override
            public void onTokenReceived(String token) {
                if (token != null) {
                    Log.d("Token", "Token: " + token);

                    String authorization = "Bearer " + token;
                    apiService.sendNotification(authorization, notificationRequest).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Log.d("FCM", "Notification sent successfully!");
                            } else {
                                Log.d("FCM", "Error: " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("FCM", "Failure: " + t.getMessage());
                        }
                    });


                } else {
                    Log.e("Token", "Failed to retrieve token.");
                }
            }
        });
    }

    private void checkNotification() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 201);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
            // Check if the permission was granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                checkNotification();
            }
        }
    }

    private void showHelpDialog() {
        DialogMessageBinding dialogBinding = DialogMessageBinding.inflate(getLayoutInflater());

        // Create a new dialog and set the custom layout
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        dialogBinding.formName.setText("Help Me");

        dialogBinding.button.setOnClickListener(v->{
            dialog.dismiss();
        });

        dialog.show();
    }
}