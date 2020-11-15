package com.tilek.data.network;

import com.tilek.data.models.CategoriesResponse;
import com.tilek.data.models.QuestionsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php")
    Call<QuestionsResponse> getQuestions(@Query("amount") int amount,
                                         @Query("category") int category,
                                         @Query("difficulty") String difficulty);

    @GET("api_category.php")
    Call<CategoriesResponse> getCategories();

}
