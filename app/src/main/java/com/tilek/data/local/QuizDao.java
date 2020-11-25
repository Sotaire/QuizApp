package com.tilek.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.tilek.data.models.QuizResult;

import java.util.Date;
import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM QuizResult")
    List<QuizResult> getAll();

    @Insert
    long insert(QuizResult result);

    @Query("SELECT *FROM QuizResult WHERE id =:id")
    QuizResult getById(int id);


    @Query("SELECT * FROM QuizResult WHERE createdAt = :dateOfGame")
    QuizResult getByDate(@TypeConverters({DateConverter.class}) Date dateOfGame);

    @Query("DELETE FROM  QuizResult")
    void deleteAll();

    @Query("DELETE FROM  QuizResult WHERE id = :id")
    void deleteById(int id);
}
