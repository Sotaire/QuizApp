package com.tilek.ui.main.quizF;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.tilek.R;
import com.tilek.data.models.Category;
import com.tilek.databinding.QuizFragmentBinding;
import com.tilek.ui.question.QuestionActivity;
import com.tilek.widjets.SimpleSeekBarChangeListener;

import java.util.ArrayList;

public class QuizFragment extends Fragment {

    private QuizViewModel mViewModel;
    public QuizFragmentBinding quizFragmentBinding;
    public final static String AMOUNT = "amount";
    public final static String ID = "id";
    public final static String DIFFICULTY = "difficulty";

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        quizFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.quiz_fragment, container, false);
        quizFragmentBinding.setLifecycleOwner(this);
        View view = quizFragmentBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        quizFragmentBinding.btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), QuestionActivity.class);
            intent.putExtra(QuizFragment.AMOUNT,quizFragmentBinding.seekbar.getProgress());
            intent.putExtra(QuizFragment.ID,mViewModel.data.getValue().get(quizFragmentBinding.spinner1.getSelectedItemPosition()).getId());
            intent.putExtra(QuizFragment.DIFFICULTY,quizFragmentBinding.spinner2.getSelectedItem().toString().toLowerCase());
            getContext().startActivity(intent);
        });
        mViewModel.getCategories();
        mViewModel.data.observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,mViewModel.names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            quizFragmentBinding.spinner1.setAdapter(adapter);
        });
        seekBarListener();
    }

    private void seekBarListener() {
        quizFragmentBinding.seekbar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               quizFragmentBinding.numbOfAmount.setText(String.valueOf(i));
            }
        });
    }

}