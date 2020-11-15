package com.tilek.data.network;

import android.util.Log;

import com.tilek.data.models.CategoriesResponse;
import com.tilek.data.models.QuestionsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizApiClient implements IQuizApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface apiInterface = retrofit.create(ApiInterface.class);

    public void getQuestions(final QuestionsApiCallback questionsApiCallback, int amount, int category, String difficulty) {
        Call<QuestionsResponse> call = apiInterface.getQuestions(amount,category,difficulty);
        call.enqueue(new Callback<QuestionsResponse>() {
            @Override
            public void onResponse(Call<QuestionsResponse> call, Response<QuestionsResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    questionsApiCallback.onSuccess(response.body().getQuestions());
                    Log.d("response","api client");
                } else {
                    questionsApiCallback.onFailure(new Exception("response is failed" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<QuestionsResponse> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }

    public void getCategories(final CategoriesApiCallback categoriesApiCallback) {
        Call<CategoriesResponse> call = apiInterface.getCategories();
        call.enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    categoriesApiCallback.onSuccess(response.body().getCategories());
                }else {
                    categoriesApiCallback.onFailure(new Exception("response is failed" + response.code()));
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                Log.d("onFailure",t.getMessage());
            }
        });
    }
}
