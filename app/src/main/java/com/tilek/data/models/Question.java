package com.tilek.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Question {

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("difficulty")
    @Expose
    private String difficulty;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("correct_answer")
    @Expose
    private String correctAnswer;

    @SerializedName("incorrect_answers")
    @Expose
    private ArrayList<String> incorrectAnswers;

    private int selectQuestionPosition = -1;

    private boolean isClicked;

    private boolean isTrue;

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getSelectQuestionPosition() {
        return selectQuestionPosition;
    }

    public void setSelectQuestionPosition(int selectQuestionPosition) {
        this.selectQuestionPosition = selectQuestionPosition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

}
