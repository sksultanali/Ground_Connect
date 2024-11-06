package com.developerali.groundconnect.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.developerali.groundconnect.Adapterts.ToolsAdapter;
import com.developerali.groundconnect.MainActivity;
import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ActivitySchemesBinding;

import java.util.ArrayList;

public class Schemes extends AppCompatActivity {

    ActivitySchemesBinding binding;
    ArrayList<ToolsModel> toolsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySchemesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolsModels.clear();
        toolsModels.add(new ToolsModel("Egiye Bangla", getDrawable(R.drawable.egiye_bangla), "https://egiyebangla.gov.in/"));//1
        toolsModels.add(new ToolsModel("KMC", getDrawable(R.drawable.kmc), "https://www.kmcgov.in/KMCPortal/jsp/KMCPortalHome1.jsp"));//2
        toolsModels.add(new ToolsModel("Sastyasathi", getDrawable(R.drawable.sastyasathi), "https://swasthyasathi.gov.in/"));//3
        toolsModels.add(new ToolsModel("Aikyashree", getDrawable(R.drawable.aikyashree), "https://wbmdfcscholarship.in/main/student_panel"));//4
        toolsModels.add(new ToolsModel("SVMCM", getDrawable(R.drawable.svmcm), "https://svmcm.co.in/"));//5
        toolsModels.add(new ToolsModel("Khadyasathi", getDrawable(R.drawable.khad), "https://food.wb.gov.in/"));//6
        toolsModels.add(new ToolsModel("Skill (Jobs)", getDrawable(R.drawable.skill_india), "https://www.skillindiadigital.gov.in/opportunities"));//7
        toolsModels.add(new ToolsModel("Suggestions", getDrawable(R.drawable.sugges), null));//8
        toolsModels.add(new ToolsModel("PartyMember", getDrawable(R.drawable.team), null));//9
        toolsModels.add(new ToolsModel("Skill India", getDrawable(R.drawable.skill), "https://www.skillindiadigital.gov.in/"));//9


        ToolsAdapter adapter = new ToolsAdapter(Schemes.this, toolsModels);
        GridLayoutManager gnm = new GridLayoutManager(Schemes.this, 3);
        binding.toolsRec.setLayoutManager(gnm);
        binding.toolsRec.setAdapter(adapter);

        binding.goBack.setOnClickListener(v->{
            finish();
        });


    }
}