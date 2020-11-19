package com.tilek.ui.result;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.ui.question.QuestionActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class ResultViewModel extends ViewModel {

    public ObservableField<String> percentField = new ObservableField<>();

    private ArrayList<Question> questions = new ArrayList<>();
    private int percent = 0;

    MutableLiveData<ArrayList<Question>> mutableLiveDataQuestions = new MutableLiveData<>();

    void getData(Intent intent){

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Question>>() {}.getType();

        questions = gson.fromJson(intent.getStringExtra(QuestionActivity.QUESTIONS),type);
        mutableLiveDataQuestions.setValue(questions);

    }

    public void getPercent(int questionsAmount,int correctAnswers){
        percent = correctAnswers  * 100 / questionsAmount;

        percentField.set(percent + " %");
    }

}
