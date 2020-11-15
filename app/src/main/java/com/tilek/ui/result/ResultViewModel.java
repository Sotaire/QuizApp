package com.tilek.ui.result;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.ui.question.QuestionActivity;

import java.util.ArrayList;
import java.util.Date;

public class ResultViewModel extends ViewModel {

    MutableLiveData<QuizResult> liveData = new MutableLiveData<>();

    void getData(Intent intent){
        String category = intent.getStringExtra(QuestionActivity.RESULT_CATEGORY);
        String difficulty = intent.getStringExtra(QuestionActivity.RESULT_DIFFICULTY);
        long date = intent.getIntExtra(QuestionActivity.CREATED_AT, 0);
        int questionsSize = intent.getIntExtra(QuestionActivity.QUESTIONS,0);
        int correctAnswers = intent.getIntExtra(QuestionActivity.CORRECT_ANSWERS, 0);

        QuizResult quizResult = new QuizResult(category,difficulty,correctAnswers,new Date(date),questionsSize);

        liveData.setValue(quizResult);
    }

}
