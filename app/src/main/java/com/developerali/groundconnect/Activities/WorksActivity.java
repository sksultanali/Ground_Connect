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
import java.util.Date;

public class WorksActivity extends AppCompatActivity {

    ActivityWorksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<PostModel> postModelArrayList = new ArrayList<>();
        postModelArrayList.clear();

        ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp");
        imgUrls.add("https://cdn.britannica.com/91/110191-050-7BCFD56B/Victoria-Memorial-Hall-Kolkata-India.jpg");
        imgUrls.add("https://media.assettype.com/outlooktraveller%2F2024-01%2Fa369e7e3-f7c8-4210-ae58-1800ea5cb2ea%2FKolkata1.jpg");

        ArrayList<String> imgUrls2 = new ArrayList<>();
        imgUrls2.add("https://www.agoda.com/wp-content/uploads/2024/01/Featured-image-Gateway-of-India-Mumbai-1244x700.jpg");
        imgUrls2.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRaJYO38vlWFaCLgZYnn8lHwMYGExZnGs6l5NDP6qq5qzL3rDUAyAWdXJfzxmEKfKb2VqY&usqp=CAU");
        imgUrls2.add("https://www.worldtravelguide.net/wp-content/uploads/2017/04/Think-India-Mumbai-486332873-Chidanand-M.-copy.jpg");

        postModelArrayList.add(new PostModel(new Date().getTime(), 20, new ArrayList<>(), "Embrace the journey of growth and self-discovery, where every step forward, no matter how small, brings you closer to the person you’re meant to be. Celebrate the wins, learn from the setbacks, and cherish each moment as a piece of your unique story. Life isn’t about perfection; it’s about progress and the beauty of resilience. Remember, every day is a new chance to rewrite your narrative, to push beyond your comfort zone, and to become the best version of yourself. Keep moving forward, and trust the process. \uD83D\uDCAB #GrowthMindset #SelfDiscovery"));
        postModelArrayList.add(new PostModel(new Date().getTime(), 20, imgUrls, "This is a test caption and generate by system!"));
        postModelArrayList.add(new PostModel(new Date().getTime(), 20, imgUrls2, null));

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