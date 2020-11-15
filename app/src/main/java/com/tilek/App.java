package com.tilek;

import android.app.Application;

import com.tilek.data.QuizRepository;
import com.tilek.data.local.IHistoryStorage;
import com.tilek.data.network.QuizApiClient;
import com.tilek.ui.main.historyF.HistoryStorage;

public class App extends Application {

    public static QuizApiClient apiClient;
    public static QuizRepository quizRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();
        IHistoryStorage historyStorage = new HistoryStorage();

        quizRepository = new QuizRepository(apiClient ,historyStorage);
    }
}
