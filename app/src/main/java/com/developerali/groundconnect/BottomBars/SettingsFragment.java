package com.developerali.groundconnect.BottomBars;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.developerali.groundconnect.Adapterts.ListAdapterVertical;
import com.developerali.groundconnect.Helpers.Helper;
import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.DialogChooseLanguageBinding;
import com.developerali.groundconnect.databinding.FragmentSettingsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSettingsBinding binding;
    ArrayList<ToolsModel> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        BottomNavigationView bottomBar = getActivity().findViewById(R.id.bottomBar);
        bottomBar.getMenu().getItem(2).setChecked(true);

        arrayList = new ArrayList<>();
        arrayList.clear();

        arrayList.add(new ToolsModel(getString(R.string.change_language),
                getActivity().getDrawable(R.drawable.lan), null));

        ListAdapterVertical adapterVertical = new ListAdapterVertical(getActivity(), arrayList);
        binding.toolsList.setAdapter(adapterVertical);


        binding.toolsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        showBottomDialog();
                        break;
                }
            }
        });


        return binding.getRoot();
    }

    private void showBottomDialog() {
        DialogChooseLanguageBinding chooseBinding = DialogChooseLanguageBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(chooseBinding.getRoot());

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        if (Helper.getLanguagePreference(getActivity()).equalsIgnoreCase("hi")){
            chooseBinding.hindi.setChecked(true);
        } else if (Helper.getLanguagePreference(getActivity()).equalsIgnoreCase("bn")) {
            chooseBinding.bengali.setChecked(true);
        }else if (Helper.getLanguagePreference(getActivity()).equalsIgnoreCase("bn")){
            chooseBinding.english.setChecked(true);
        }else if (Helper.getLanguagePreference(getActivity()).equalsIgnoreCase("ur")){
            chooseBinding.urdu.setChecked(true);
        }

        chooseBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.english) {
                    Helper.saveLanguagePreference(getActivity(), "en");
                    Helper.updateLocale(getActivity(), Helper.getLanguagePreference(getActivity()));
                    dialog.dismiss();
                    getActivity().recreate();
                } else if (checkedId == R.id.hindi) {
                    Helper.saveLanguagePreference(getActivity(), "hi");
                    Helper.updateLocale(getActivity(), Helper.getLanguagePreference(getActivity()));
                    dialog.dismiss();
                    getActivity().recreate();
                }else if (checkedId == R.id.urdu) {
                    Helper.saveLanguagePreference(getActivity(), "ur");
                    Helper.updateLocale(getActivity(), Helper.getLanguagePreference(getActivity()));
                    dialog.dismiss();
                    getActivity().recreate();
                }else {
                    Helper.saveLanguagePreference(getActivity(), "bn");
                    Helper.updateLocale(getActivity(), Helper.getLanguagePreference(getActivity()));
                    dialog.dismiss();
                    getActivity().recreate();
                }
            }
        });

        dialog.show();
    }
}