package com.developerali.groundconnect.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developerali.groundconnect.Adapterts.PostAdapter;
import com.developerali.groundconnect.MainActivity;
import com.developerali.groundconnect.Models.PostModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ActivityWorksBinding;

import java.util.ArrayList;

public class WorksActivity extends AppCompatActivity {

    ActivityWorksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<PostModel> postModelArrayList = new ArrayList<>();
        postModelArrayList.clear();
        postModelArrayList.add(new PostModel());
        postModelArrayList.add(new PostModel());
        postModelArrayList.add(new PostModel());
        postModelArrayList.add(new PostModel());

        PostAdapter postAdapter = new PostAdapter(WorksActivity.this, postModelArrayList, postModelArrayList.size());
        LinearLayoutManager lnm = new LinearLayoutManager(WorksActivity.this);
        lnm.setOrientation(RecyclerView.VERTICAL);
        binding.worksRec.setLayoutManager(lnm);
        binding.worksRec.setAdapter(postAdapter);

        binding.goBack.setOnClickListener(v->{
            finish();
        });



    }
}