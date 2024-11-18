package com.developerali.groundconnect.Activities;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.developerali.groundconnect.ApiRes.ApiResponse;
import com.developerali.groundconnect.ApiRes.ApiService;
import com.developerali.groundconnect.Models.Member;
import com.developerali.groundconnect.RetrofitClient;
import com.developerali.groundconnect.databinding.ActivityReelsPostBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReelsPostActivity extends AppCompatActivity {

    ActivityReelsPostBinding binding;
    private static final int PICK_VIDEO = 1;
    MediaController mediaController;
    String arr[]={"Select", "Entertainment", "Hot", "Filmy", "Comedy"};
    String selectedItem;
    StorageReference storageReference;
    DatabaseReference allShorts, userbasisshorts, categorybasisshorts;
    String description, VideoUrl, uid, serach;
    int videoViews;
    ProgressDialog dialog;
    UploadTask uploadTask;
    FirebaseDatabase database;
    DatabaseReference dr;
    String username, videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReelsPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        storageReference = FirebaseStorage.getInstance().getReference("videos");
        allShorts = FirebaseDatabase.getInstance().getReference("videos");

        database = FirebaseDatabase.getInstance();
        dr = database.getReference().child("users");


        mediaController = new MediaController(ReelsPostActivity.this);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.start();


        //last step for diallog (optional)
        dialog = new ProgressDialog(ReelsPostActivity.this);
        dialog.setMessage("Uploading Video... ");
        dialog.setCancelable(false);

        //spinner
        ArrayAdapter<String> obj=new ArrayAdapter<String>(ReelsPostActivity.this, android.R.layout.simple_spinner_item,arr);
        binding.spinner.setAdapter(obj);
        //spinner click here
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem= arr[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //chose video button
        binding.choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("video/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, PICK_VIDEO);
            }
        });

        //getting UserName
        getData();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                description = binding.description.getText().toString();
                videoViews = 0;

                dialog.show();

                if (videoUrl != null){
                    Member member = new Member(description, videoUrl, username, videoViews);
                    String i = allShorts.push().getKey();
                    allShorts.child(i).setValue(member).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            dialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(ReelsPostActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    dialog.dismiss();
                    Toast.makeText(ReelsPostActivity.this, "video must be selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    public void getData(){
        //My Videos List
        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username = String.valueOf(snapshot.child("userName").getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String getExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_VIDEO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri videoUri = data.getData();

            binding.videoView.setVideoURI(videoUri);

            // Use ContentResolver to open InputStream
            try {
                InputStream inputStream = getContentResolver().openInputStream(videoUri);
                // Do something with inputStream, like upload to a server
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String filePath = copyFileToInternalStorage(videoUri, "myVideo.mp4");
            if (filePath != null) {
                uploadVideo(filePath);
                Log.d("VideoFilePath", "File saved to internal storage, path: " + filePath);
            }
        }
    }

    private String copyFileToInternalStorage(Uri uri, String newFileName) {
        File file = new File(getFilesDir(), newFileName);
        try (InputStream inputStream = getContentResolver().openInputStream(uri);
             OutputStream outputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[4096];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file.getAbsolutePath();
    }

    private void uploadVideo(String filePath) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        File file = new File(filePath);

        if (!file.exists()) {
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();

        // Prepare file part
        RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // Call the API
        Call<ApiResponse> call = apiService.uploadFile(
                "fa3b2c9c-a96d-48a8-82ad-0cb775dd3e5d",
                body);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                dialog.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    videoUrl = response.body().getFileUrl();
                    Toast.makeText(ReelsPostActivity.this, "File uploaded successfully!", Toast.LENGTH_SHORT).show();
                    Log.d("FileUploader", "File uploaded. URL: " + videoUrl);
                } else {
                    Toast.makeText(ReelsPostActivity.this, "Upload failed", Toast.LENGTH_LONG).show();
                    Log.e("FileUploader", "Upload failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ReelsPostActivity.this, "Upload error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FileUploader", "Upload error: " + t.getMessage());
            }
        });
    }

    // Helper function to get the file path from Uri
    private String getPathFromUri(Uri uri) {
        String path = null;
        String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            path = cursor.getString(column_index);
            cursor.close();
        }
        return path;
    }


}