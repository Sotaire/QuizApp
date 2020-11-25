package com.tilek.ui.main.historyF;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tilek.App;
import com.tilek.R;
import com.tilek.adapters.quizA.HistoryAdapter;
import com.tilek.data.models.QuizResult;
import com.tilek.databinding.HistoryFragmentBinding;
import com.tilek.databinding.HistoryHolderBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private HistoryViewModel mViewModel;
    HistoryAdapter historyAdapter;
    HistoryFragmentBinding historyHolderBinding;

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        historyHolderBinding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return historyHolderBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyAdapter = new HistoryAdapter();
        historyHolderBinding.recyclerHistory.setAdapter(historyAdapter);
        historyAdapter.setQuizResults((ArrayList<QuizResult>) App.quizDatabase.quizDao().getAll());
    }

}