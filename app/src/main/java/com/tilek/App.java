package com.tilek;

import android.app.Application;

import androidx.room.Room;

import com.tilek.data.QuizRepository;
import com.tilek.data.local.HistoryStorage;
import com.tilek.data.local.IHistoryStorage;
import com.tilek.data.local.QuizDatabase;
import com.tilek.data.network.QuizApiClient;

public class App extends Application {

    public static QuizApiClient apiClient;
    public static QuizRepository quizRepository;
    public static QuizDatabase quizDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();

        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "QuizDatabase"
        ).allowMainThreadQueries()
                .build();

        IHistoryStorage iHistoryStorage = new HistoryStorage();

        quizRepository = new QuizRepository(apiClient ,iHistoryStorage);
    }
}
