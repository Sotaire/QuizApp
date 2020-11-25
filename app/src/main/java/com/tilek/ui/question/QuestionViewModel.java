package com.tilek.ui.question;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tilek.App;
import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.data.network.IQuizApiClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Question>> questionLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    ArrayList<String> categories = new ArrayList<>();
    public ArrayList<Question> mQuestion;

    int correctAnswers;

    MutableLiveData<Integer> isLast = new MutableLiveData<>();

    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public void getQuestions(int amount,int id,String difficulty){
        isLoading.set(true);
        App.quizRepository.getQuestions(new IQuizApiClient.QuestionsApiCallback() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    categories.add(result.get(i).getCategory());
                }
                mQuestion = result;
                Log.d("response",String.valueOf(mQuestion.get(0).getIncorrectAnswers().size()) + "got");
                questionLiveData.setValue(mQuestion);
                isLoading.set(false);
                currentQuestionPosition.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("onFailure",e.getMessage());
                isLoading.set(false);
            }
        },amount,id,difficulty);
    }

    public void moveToQuestionFinish(int position){
        if (position == mQuestion.size() - 1){
            finish();
        }else {
            currentQuestionPosition.setValue(position);
        }
    }

    void finish(){
        isLast.setValue(correctAnswers);
    }

    void skip(int questionPosition){
        if (questionPosition == mQuestion.size() - 1){
            return;
        }else{
        mQuestion.get(questionPosition).setClicked(true);
        questionLiveData.setValue(mQuestion);
        }
    }

    public void onAnswerClick(int questionPosition,int answerPosition){
        if (currentQuestionPosition.getValue() == null || mQuestion == null){
            return;
        }

        Question question = mQuestion.get(questionPosition);

        question.setSelectQuestionPosition(answerPosition);

        question.setClicked(true);

        if (question.getIncorrectAnswers().get(answerPosition).equals(question.getCorrectAnswer())) {
            correctAnswers++;
            question.setAnswerTrue(true);
        }else{
            question.setAnswerTrue(false);
        }

        mQuestion.set(questionPosition,question);

//        mQuestion.get(questionPosition).setClicked(true);

        questionLiveData.setValue(mQuestion);
    }
}
