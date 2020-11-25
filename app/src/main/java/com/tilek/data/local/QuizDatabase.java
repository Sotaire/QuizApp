package com.tilek.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tilek.data.models.QuizResult;

@Database(entities = {QuizResult.class},version = 1,exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase{
    public abstract QuizDao quizDao();
}
