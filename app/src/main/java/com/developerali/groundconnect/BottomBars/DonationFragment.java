package com.developerali.groundconnect.BottomBars;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.FragmentDonationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class DonationFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentDonationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDonationBinding.inflate(inflater, container, false);
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottomBar);
        bottomBar.getMenu().getItem(2).setChecked(true);



        return binding.getRoot();
    }
}