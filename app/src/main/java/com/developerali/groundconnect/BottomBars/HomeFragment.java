package com.developerali.groundconnect.BottomBars;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.developerali.groundconnect.Activities.Schemes;
import com.developerali.groundconnect.Activities.WorksActivity;
import com.developerali.groundconnect.Adapterts.PostAdapter;
import com.developerali.groundconnect.Adapterts.SmallAdapter;
import com.developerali.groundconnect.Adapterts.ToolsAdapter;
import com.developerali.groundconnect.Adapterts.TopStatusAdapter;
import com.developerali.groundconnect.Models.Member;
import com.developerali.groundconnect.Models.PostModel;
import com.developerali.groundconnect.Models.Status;
import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.Models.UserStatus;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.FragmentHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentHomeBinding binding;
    ArrayList<ToolsModel> toolsModels = new ArrayList<>();
    private static final String BASE_URL = "https://fcm.googleapis.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottomBar);
        bottomBar.getMenu().getItem(0).setChecked(true);


        //Tools Start
        toolsModels.clear();
        toolsModels.add(new ToolsModel("Egiye Bangla", getActivity().getDrawable(R.drawable.egiye_bangla), "https://egiyebangla.gov.in/"));//1
        toolsModels.add(new ToolsModel("KMC", getActivity().getDrawable(R.drawable.kmc), "https://www.kmcgov.in/KMCPortal/jsp/KMCPortalHome1.jsp"));//2
        toolsModels.add(new ToolsModel("Sastyasathi", getActivity().getDrawable(R.drawable.sastyasathi), "https://swasthyasathi.gov.in/"));//3
        toolsModels.add(new ToolsModel("Aikyashree", getActivity().getDrawable(R.drawable.aikyashree), "https://wbmdfcscholarship.in/main/student_panel"));//4
        toolsModels.add(new ToolsModel("SVMCM", getActivity().getDrawable(R.drawable.svmcm), "https://svmcm.co.in/"));//5
        toolsModels.add(new ToolsModel("Khadyasathi", getActivity().getDrawable(R.drawable.khad), "https://food.wb.gov.in/"));

        ToolsAdapter adapter = new ToolsAdapter(getActivity(), toolsModels);
        GridLayoutManager gnm = new GridLayoutManager(getActivity(), 3);
        binding.toolsRec.setLayoutManager(gnm);
        binding.toolsRec.setAdapter(adapter);
        //Tools End


        //Banner Start
        final ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.clear();
        slideModels.add(new SlideModel(R.drawable.bannerb, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.bannera, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.bannerd, ScaleTypes.CENTER_CROP));
        binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        //Banner End


        //Single Post Start
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

        PostAdapter postAdapter = new PostAdapter(getActivity(), postModelArrayList, postModelArrayList.size());
        LinearLayoutManager lnm = new LinearLayoutManager(getActivity());
        lnm.setOrientation(RecyclerView.VERTICAL);
        binding.worksRec.setLayoutManager(lnm);
        binding.worksRec.setAdapter(postAdapter);
        //Single Post End


        //Status Start
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        binding.statusList.setLayoutManager(layoutManager);

        binding.statusList.showShimmerAdapter();
        ArrayList<UserStatus> userStatuses = new ArrayList<>();

        ArrayList<Status> statuses = new ArrayList<>();
        statuses.add(new Status("https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp", new Date().getTime()));
        statuses.add(new Status("https://cdn.britannica.com/91/110191-050-7BCFD56B/Victoria-Memorial-Hall-Kolkata-India.jpg", new Date().getTime()));
        statuses.add(new Status("https://media.assettype.com/outlooktraveller%2F2024-01%2Fa369e7e3-f7c8-4210-ae58-1800ea5cb2ea%2FKolkata1.jpg", new Date().getTime()));

        userStatuses.clear();
        userStatuses.add(new UserStatus("name", "https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp",  new Date().getTime(), statuses));
        userStatuses.add(new UserStatus("name", "https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp",  new Date().getTime(), statuses));
        userStatuses.add(new UserStatus("name", "https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp",  new Date().getTime(), statuses));
        userStatuses.add(new UserStatus("name", "https://www.millenniumpost.in/h-upload/2024/10/28/814954-pic-for-the-kolkata-city-view-ranks.webp",  new Date().getTime(), statuses));

        TopStatusAdapter statusAdapter = new TopStatusAdapter(getActivity(), userStatuses);
        binding.statusList.setAdapter(statusAdapter);
        binding.statusList.hideShimmerAdapter();
        //Status End

        binding.seeMoreWork.setOnClickListener(v->{
            Intent i = new Intent(getActivity().getApplicationContext(), WorksActivity.class);
            startActivity(i);
        });

        binding.seeSchemes.setOnClickListener(v->{
            startActivity(new Intent(getActivity().getApplicationContext(), Schemes.class));
        });

        binding.seeReels.setOnClickListener(v->{
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, new ShortsFragment()).addToBackStack(null);
            transaction.commit();
        });



        // Reels Start
        ArrayList<Member> rModels = new ArrayList<>();
        rModels.clear();
        rModels.add(new Member("description",
                "https://firebasestorage.googleapis.com/v0/b/bong-receipt.appspot.com/o/uploads%2Fvideoplayback%20(3).mp4?alt=media&token=42a42b49-044d-43f5-92ce-1ee18b731d3b",
                "userName", 0));
        rModels.add(new Member("description",
                "https://firebasestorage.googleapis.com/v0/b/bong-receipt.appspot.com/o/uploads%2FMamata%20Banerjee%20Plays%20%E2%80%98Hum%20Honge%20Kamyaab%E2%80%99%20On%20An%20Accordion%20In%20Spain%E2%80%99s%20Madrid%20shorts%20mamatabanerjee.mp4?alt=media&token=5ab79e3b-0f2e-4e76-bed5-4b8fb9441c6e",
                "userName", 0));
        rModels.add(new Member("description",
                "https://firebasestorage.googleapis.com/v0/b/bong-receipt.appspot.com/o/uploads%2Fvideoplayback%20(1).mp4?alt=media&token=eb0eb188-7789-44b7-87a4-348c8a88a129",
                "userName", 0));
        rModels.add(new Member("description",
                "https://firebasestorage.googleapis.com/v0/b/bong-receipt.appspot.com/o/uploads%2Fvideoplayback%20(3).mp4?alt=media&token=42a42b49-044d-43f5-92ce-1ee18b731d3b",
                "userName", 0));

        SmallAdapter pAdapter = new SmallAdapter(getContext(), rModels, getActivity());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false); // Set horizontal orientation
        binding.reelsRec.setLayoutManager(layoutManager1); // Use the correctly defined layoutManager1
        binding.reelsRec.setAdapter(pAdapter);
        // Reels End



        return binding.getRoot();
    }
}