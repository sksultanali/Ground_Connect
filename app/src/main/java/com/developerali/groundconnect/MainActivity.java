package com.developerali.groundconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.developerali.groundconnect.Activities.Schemes;
import com.developerali.groundconnect.Activities.WorksActivity;
import com.developerali.groundconnect.Adapterts.PostAdapter;
import com.developerali.groundconnect.Adapterts.ToolsAdapter;
import com.developerali.groundconnect.Models.PostModel;
import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<ToolsModel> toolsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolsModels.clear();
        toolsModels.add(new ToolsModel("Egiye Bangla", getDrawable(R.drawable.egiye_bangla), "https://egiyebangla.gov.in/"));//1
        toolsModels.add(new ToolsModel("KMC", getDrawable(R.drawable.kmc), "https://www.kmcgov.in/KMCPortal/jsp/KMCPortalHome1.jsp"));//2
        toolsModels.add(new ToolsModel("Sastyasathi", getDrawable(R.drawable.sastyasathi), "https://swasthyasathi.gov.in/"));//3
        toolsModels.add(new ToolsModel("Aikyashree", getDrawable(R.drawable.aikyashree), "https://wbmdfcscholarship.in/main/student_panel"));//4
        toolsModels.add(new ToolsModel("SVMCM", getDrawable(R.drawable.svmcm), "https://svmcm.co.in/"));//5
        toolsModels.add(new ToolsModel("Khadyasathi", getDrawable(R.drawable.khad), "https://food.wb.gov.in/"));//6
        //toolsModels.add(new ToolsModel("Skill (Jobs)", getDrawable(R.drawable.skill_india), "https://www.skillindiadigital.gov.in/opportunities"));//7
        //toolsModels.add(new ToolsModel("Suggestions", getDrawable(R.drawable.sugges), null));//8

        ToolsAdapter adapter = new ToolsAdapter(MainActivity.this, toolsModels);
        GridLayoutManager gnm = new GridLayoutManager(MainActivity.this, 3);
        binding.toolsRec.setLayoutManager(gnm);
        binding.toolsRec.setAdapter(adapter);

        binding.seeSchemes.setOnClickListener(v->{
            startActivity(new Intent(MainActivity.this, Schemes.class));
        });

        final ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.clear();
        slideModels.add(new SlideModel(R.drawable.bannerb, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.bannerb, ScaleTypes.CENTER_CROP));
        binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT);


        ArrayList<PostModel> postModelArrayList = new ArrayList<>();
        postModelArrayList.clear();
        postModelArrayList.add(new PostModel());
        postModelArrayList.add(new PostModel());
        postModelArrayList.add(new PostModel());

        PostAdapter postAdapter = new PostAdapter(MainActivity.this, postModelArrayList, postModelArrayList.size());
        LinearLayoutManager lnm = new LinearLayoutManager(MainActivity.this);
        lnm.setOrientation(RecyclerView.VERTICAL);
        binding.worksRec.setLayoutManager(lnm);
        binding.worksRec.setAdapter(postAdapter);

        binding.seeMoreWork.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, WorksActivity.class);
            startActivity(i);
        });




    }
}