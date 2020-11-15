package com.tilek.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tilek.R;
import com.tilek.data.models.QuizResult;
import com.tilek.databinding.ActivityResultBinding;
import com.tilek.ui.question.QuestionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResultActivity extends AppCompatActivity {

    ResultViewModel resultViewModel;
    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);

        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);

        resultViewModel.getData(getIntent());

        resultViewModel.liveData.observe(this, new Observer<QuizResult>() {
            @Override
            public void onChanged(QuizResult quizResult) {
                binding.setResult(quizResult);
            }
        });
    }

    void getDate(long time){
        String dateString = new SimpleDateFormat("MM/dd/yyyy").format(time);
    }

}