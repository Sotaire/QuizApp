package com.tilek.data.local;

import androidx.room.Room;

import com.tilek.data.models.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    QuizDao quizDao;

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public List<QuizResult> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
