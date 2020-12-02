package com.tilek.ui.main.settingsF;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilek.App;
import com.tilek.R;
import com.tilek.data.local.prefs.Prefs;
import com.tilek.databinding.SettingsFragmentBinding;
import com.tilek.ui.SplashActivity;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    SettingsFragmentBinding settingsFragmentBinding;
    Prefs prefs;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        settingsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false);
        return settingsFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        settingsFragmentBinding.layout4.setOnClickListener(view -> {
            App.quizDatabase.quizDao().deleteAll();
        });
        prefs = new Prefs(getActivity().getApplicationContext());
        settingsFragmentBinding.darkTheme.setOnClickListener(view -> {
            prefs.setTheme(1);
            getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        });
        settingsFragmentBinding.lightTheme.setOnClickListener(view -> {
            prefs.setTheme(0);
            getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        });
    }

}