package com.tilek.adapters.quizA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tilek.data.models.Question;
import com.tilek.databinding.QuestionHolderBinding;

import java.util.ArrayList;
import java.util.Collections;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    ArrayList<Question> questions = new ArrayList<>();
    OnBtnClickListener listener;

    public void setListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionHolderBinding binding = QuestionHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuizViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionHolderBinding.setListener(listener);
        holder.questionHolderBinding.setQuestion(question);
        if (question.getType().equals("multiple")){
            if (question.isClicked()){
                holder.questionHolderBinding.btnOne.setClickable(false);
                holder.questionHolderBinding.btnTwo.setClickable(false);
                holder.questionHolderBinding.btnThree.setClickable(false);
                holder.questionHolderBinding.btnFour.setClickable(false);
            }
            holder.questionHolderBinding.btnOne.setText(question.getIncorrectAnswers().get(0).toString());
            holder.questionHolderBinding.btnTwo.setText(question.getIncorrectAnswers().get(1).toString());
            holder.questionHolderBinding.btnThree.setText(question.getIncorrectAnswers().get(2).toString());
            holder.questionHolderBinding.btnFour.setText(question.getIncorrectAnswers().get(3).toString());
        }else {
            if (question.isClicked()){
                holder.questionHolderBinding.btnNoBoolean.setClickable(false);
                holder.questionHolderBinding.btnYesBoolean.setClickable(false);
            }
            holder.questionHolderBinding.btnNoBoolean.setText(question.getIncorrectAnswers().get(0).toString());
            holder.questionHolderBinding.btnYesBoolean.setText(question.getIncorrectAnswers().get(1).toString());
        }
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        QuestionHolderBinding questionHolderBinding;

        public QuizViewHolder(@NonNull View view) {
            super(view);
            questionHolderBinding = DataBindingUtil.bind(view);
            questionHolderBinding.btnOne.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),0);
            });
            questionHolderBinding.btnTwo.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),1);
            });
            questionHolderBinding.btnThree.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),2);
            });
            questionHolderBinding.btnFour.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),3);
            });
            questionHolderBinding.btnNoBoolean.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),0);
            });
            questionHolderBinding.btnYesBoolean.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition(),1);
            });
        }
    }
}
