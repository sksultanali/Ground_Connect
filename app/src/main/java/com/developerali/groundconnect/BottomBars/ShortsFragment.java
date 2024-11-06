package com.developerali.groundconnect.BottomBars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;

import com.developerali.groundconnect.Adapterts.videoadapter;
import com.developerali.groundconnect.Models.Member;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.FragmentShortsBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class ShortsFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentShortsBinding binding;
    videoadapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShortsBinding.inflate(inflater, container, false);

        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottomBar);
        bottomBar.getMenu().getItem(1).setChecked(true);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseRecyclerOptions<Member> options = new FirebaseRecyclerOptions.Builder<Member>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("videos"), Member.class)
                .build();

        adapter = new videoadapter(options);
        binding.vpager.setAdapter(adapter);





        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}