package com.tilek.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tilek.R;
import com.tilek.databinding.ActivityMainBinding;
import com.tilek.adapters.fargmentsA.FragmentsAdapter;
import com.tilek.ui.main.historyF.HistoryFragment;
import com.tilek.ui.main.quizF.QuizFragment;
import com.tilek.ui.main.settingsF.SettingsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> fragments;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        fragments = new ArrayList<>();
        fiilFragment();
        activityMainBinding.mainViewPager.setAdapter(new FragmentsAdapter(fragments,getSupportFragmentManager()));
        bottomListener();
    }

    private void bottomListener() {
        activityMainBinding.mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.quiz_icon){
                    activityMainBinding.mainViewPager.setCurrentItem(0);
                }else if (item.getItemId() == R.id.history_icon){
                    activityMainBinding.mainViewPager.setCurrentItem(1);
                }else if (item.getItemId() == R.id.settings_icon){
                    activityMainBinding.mainViewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }

    private void fiilFragment() {
        fragments.add(new QuizFragment());
        fragments.add(new HistoryFragment());
        fragments.add(new SettingsFragment());
    }

}