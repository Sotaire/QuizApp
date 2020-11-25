package com.tilek.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.tilek.R;
import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.databinding.ActivityResultBinding;
import com.tilek.ui.question.QuestionActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    ResultViewModel resultViewModel;
    ActivityResultBinding binding;
    QuizResult quizResult;
    int correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);

        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);

        binding.setViewModel(resultViewModel);

        correctAnswers = getIntent().getIntExtra(QuestionActivity.CORRECT_ANSWERS,0);
        resultViewModel.getData(getIntent());

        resultViewModel.mutableLiveDataQuestions.observe(this, new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                if (questions != null && questions.size()>0){
                    quizResult = new QuizResult(questions.get(0).getCategory()
                            ,questions.get(0).getDifficulty()
                            ,correctAnswers
                            ,new Date(System.currentTimeMillis())
                            ,questions.size()
                            ,questions);
                    resultViewModel.getPercent(questions.size(),correctAnswers);
                    binding.setResult(quizResult);
                }
            }
        });

        binding.btnFinish.setOnClickListener(view -> {
            resultViewModel.saveResultToDB(quizResult);
            finish();
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}