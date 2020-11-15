package com.tilek.ui.main.quizF;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tilek.App;
import com.tilek.data.models.Category;
import com.tilek.data.network.IQuizApiClient;
import com.tilek.ui.question.QuestionActivity;

import java.util.ArrayList;

public class QuizViewModel extends ViewModel {

    public  MutableLiveData<ArrayList<Category>> data = new MutableLiveData<>();
    public String[] names;

    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public void onClick(View view,int amount,int id,String difficulty) {

    }

    public void getCategories(){
        isLoading.set(true);
        App.quizRepository.getCategories(new IQuizApiClient.CategoriesApiCallback() {
            @Override
            public void onSuccess(ArrayList<Category> result) {
                names = new String[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    names[i] = result.get(i).getName();
                }
                data.setValue(result);
                isLoading.set(false);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("quizF",e.getMessage());
                isLoading.set(false);
            }
        });
    }

}