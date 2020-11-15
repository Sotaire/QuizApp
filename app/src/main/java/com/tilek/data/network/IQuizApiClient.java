package com.tilek.data.network;

import com.tilek.adapters.baseA.IBaseCallback;
import com.tilek.data.models.Category;
import com.tilek.data.models.Question;

import java.util.ArrayList;

public interface IQuizApiClient {

    void getQuestions(QuestionsApiCallback questionsApiCallback,int amount,int category,String difficulty);
    void getCategories(CategoriesApiCallback categoriesApiCallback);

    interface QuestionsApiCallback extends IBaseCallback<ArrayList<Question>> {
        @Override
        void onSuccess(ArrayList<Question> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoriesApiCallback extends IBaseCallback<ArrayList<Category>>{
        @Override
        void onSuccess(ArrayList<Category> result);

        @Override
        void onFailure(Exception e);
    }


}
