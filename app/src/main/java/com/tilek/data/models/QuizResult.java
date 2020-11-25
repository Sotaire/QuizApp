package com.tilek.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.tilek.data.local.DateConverter;
import com.tilek.data.local.QuestionsConverter;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class QuizResult {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "difficulty")
    private String difficulty;
    @ColumnInfo(name = "correctAnswers")
    private int correctAnswers;
    @TypeConverters(DateConverter.class)
    private Date createdAt;
    @TypeConverters(QuestionsConverter.class)
    private ArrayList<Question> questions;
    @ColumnInfo(name = "size")
    int size;

    public QuizResult(String category, String difficulty, int correctAnswers, Date createdAt,int size,ArrayList<Question> questions) {
        this.category = category;
        this.difficulty = difficulty;
        this.correctAnswers = correctAnswers;
        this.createdAt = createdAt;
        this.size = size;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
