package com.tilek.adapters.quizA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tilek.data.models.QuizResult;
import com.tilek.databinding.HistoryHolderBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    ArrayList<QuizResult> quizResults = new ArrayList<>();
    HistoryHolderBinding holderBinding;

    public void setQuizResults(ArrayList<QuizResult> quizResults) {
        this.quizResults = quizResults;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        holderBinding = HistoryHolderBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new HistoryViewHolder(holderBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.binding.setQuizResult(quizResults.get(position));
        holder.binding.hDateTv.setText(getDate(quizResults.get(position).getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return quizResults.size();
    }

    String getDate(Date time){
        return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(time);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{

        HistoryHolderBinding binding;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
