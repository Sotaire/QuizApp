package com.tilek.adapters.quizA;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tilek.R;
import com.tilek.data.models.Question;
import com.tilek.databinding.QuestionHolderBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Handler;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    ArrayList<Question> questions = new ArrayList<>();

    public void setBtns(ArrayList<Button> btns) {
        this.btns = btns;
    }

    ArrayList<Button> btns;
    OnBtnClickListener listener;
    QuestionHolderBinding binding;
    private QuizViewHolder holder1;

    public void setListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = QuestionHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuizViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder1 = holder;
        Question question = questions.get(position);
        holder.clear();
        holder.questionHolderBinding.setListener(listener);
        holder.questionHolderBinding.setQuestion(question);
        if (question.getType().equals("multiple")) {
            if (question.getSelectQuestionPosition() == 0) {
                if (question.isTrue()) {
                    holder.questionHolderBinding.btnOne.setBackgroundResource(R.drawable.question_right_back);
                    holder.questionHolderBinding.btnNoBoolean.setBackgroundResource(R.drawable.question_right_back);
                } else {
                    holder.questionHolderBinding.btnOne.setBackgroundResource(R.drawable.question_wrong);
                    holder.questionHolderBinding.btnNoBoolean.setBackgroundResource(R.drawable.question_wrong);
                }
                holder.questionHolderBinding.btnOne.setTextColor(Color.WHITE);
                holder.questionHolderBinding.btnNoBoolean.setTextColor(Color.WHITE);
            }
            if (question.getSelectQuestionPosition() == 1) {
                if (question.isTrue()) {
                    holder.questionHolderBinding.btnTwo.setBackgroundResource(R.drawable.question_right_back);
                    holder.questionHolderBinding.btnYesBoolean.setBackgroundResource(R.drawable.question_right_back);
                } else {
                    holder.questionHolderBinding.btnNoBoolean.setBackgroundResource(R.drawable.question_wrong);
                    holder.questionHolderBinding.btnTwo.setBackgroundResource(R.drawable.question_wrong);
                }
                holder.questionHolderBinding.btnYesBoolean.setTextColor(Color.WHITE);
                holder.questionHolderBinding.btnTwo.setTextColor(Color.WHITE);
            }
            if (question.getSelectQuestionPosition() == 2) {
                if (question.isTrue()) {
                    holder.questionHolderBinding.btnThree.setBackgroundResource(R.drawable.question_right_back);
                } else {
                    holder.questionHolderBinding.btnThree.setBackgroundResource(R.drawable.question_wrong);
                }
                holder.questionHolderBinding.btnThree.setTextColor(Color.WHITE);
            }
            if (question.getSelectQuestionPosition() == 3) {
                if (question.isTrue()) {
                    holder.questionHolderBinding.btnFour.setBackgroundResource(R.drawable.question_right_back);
                } else {
                    holder.questionHolderBinding.btnFour.setBackgroundResource(R.drawable.question_wrong);
                }
                holder.questionHolderBinding.btnFour.setTextColor(Color.WHITE);
            }
            holder.questionHolderBinding.btnOne.setText(question.getIncorrectAnswers().get(0).toString());
            holder.questionHolderBinding.btnTwo.setText(question.getIncorrectAnswers().get(1).toString());
            holder.questionHolderBinding.btnThree.setText(question.getIncorrectAnswers().get(2).toString());
            holder.questionHolderBinding.btnFour.setText(question.getIncorrectAnswers().get(3).toString());
        } else {
            holder.questionHolderBinding.btnNoBoolean.setText(question.getIncorrectAnswers().get(0).toString());
            holder.questionHolderBinding.btnYesBoolean.setText(question.getIncorrectAnswers().get(1).toString());
        }
    }


    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    public QuizViewHolder getHolder1() {
        return holder1;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        public QuestionHolderBinding questionHolderBinding;

        public QuizViewHolder(@NonNull View view) {
            super(view);
            questionHolderBinding = DataBindingUtil.bind(view);
            questionHolderBinding.btnOne.setOnClickListener(v -> {
                questionHolderBinding.btnOne.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnOne.setTextColor(Color.WHITE);
                listener.onClick(getAdapterPosition(), 0);
            });
            questionHolderBinding.btnTwo.setOnClickListener(v -> {
                questionHolderBinding.btnTwo.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnTwo.setTextColor(Color.WHITE);
                ;
                listener.onClick(getAdapterPosition(), 1);
            });
            questionHolderBinding.btnThree.setOnClickListener(v -> {
                questionHolderBinding.btnThree.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnThree.setTextColor(Color.WHITE);
                ;
                listener.onClick(getAdapterPosition(), 2);
            });
            questionHolderBinding.btnFour.setOnClickListener(v -> {
                questionHolderBinding.btnFour.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnFour.setTextColor(Color.WHITE);
                ;
                listener.onClick(getAdapterPosition(), 3);
            });
            questionHolderBinding.btnNoBoolean.setOnClickListener(v -> {
                questionHolderBinding.btnNoBoolean.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnNoBoolean.setTextColor(Color.WHITE);
                ;
                listener.onClick(getAdapterPosition(), 0);
            });
            questionHolderBinding.btnYesBoolean.setOnClickListener(v -> {
                questionHolderBinding.btnYesBoolean.setBackgroundResource(R.drawable.question_click);
                questionHolderBinding.btnYesBoolean.setTextColor(Color.WHITE);
                ;
                listener.onClick(getAdapterPosition(), 1);
            });
        }

        public void clear() {
            if (btns != null) {
                for (int i = 0; i < btns.size(); i++) {
                    btns.get(i).setBackgroundResource(R.drawable.question_btn_back);
                    btns.get(i).setTextColor(Color.parseColor("#6979F8"));
                }
            }
        }
    }
}
