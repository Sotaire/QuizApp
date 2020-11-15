package com.tilek.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.tilek.adapters.baseA.IBaseCallback;
import com.tilek.data.local.IHistoryStorage;
import com.tilek.data.local.QuestionsConverter;
import com.tilek.data.models.Category;
import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.data.network.IQuizApiClient;
import com.tilek.data.network.QuizApiClient;

import java.util.ArrayList;
import java.util.Collections;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage iHistoryStorage;
    @TypeConverters(QuestionsConverter.class)
    Question question = new Question();

    public QuizRepository(QuizApiClient quizApiClient, IHistoryStorage iHistoryStorage) {
        this.quizApiClient = quizApiClient;
        this.iHistoryStorage = iHistoryStorage;
    }

    @Override
    public void getQuestions(QuestionsApiCallback apiCallback, int amount, int category, String difficulty) {
        quizApiClient.getQuestions(new QuestionsApiCallback() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    Question question = result.get(i);
                    ArrayList<String> answers = new ArrayList<>(question.getIncorrectAnswers());
                    answers.add(question.getCorrectAnswer());
                    Collections.shuffle(answers);
                    result.get(i).setIncorrectAnswers(answers);
                    Log.d("response", result.get(i).getIncorrectAnswers().size() + " repository");
                }
                question = null;
                apiCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                question = null;
                apiCallback.onFailure(e);
            }
        }, amount, category, difficulty);
    }

    @Override
    public void getCategories(IQuizApiClient.CategoriesApiCallback categoriesApiCallback) {
        quizApiClient.getCategories(new CategoriesApiCallback() {
            @Override
            public void onSuccess(ArrayList<Category> result) {
                categoriesApiCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                categoriesApiCallback.onFailure(e);
            }
        });
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<ArrayList<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
