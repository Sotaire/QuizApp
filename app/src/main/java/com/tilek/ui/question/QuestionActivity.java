package com.tilek.ui.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.gson.Gson;
import com.tilek.R;
import com.tilek.data.models.Question;
import com.tilek.data.models.QuizResult;
import com.tilek.databinding.ActivityQuestionBinding;
import com.tilek.adapters.quizA.OnBtnClickListener;
import com.tilek.adapters.quizA.QuizAdapter;
import com.tilek.ui.main.quizF.QuizFragment;
import com.tilek.ui.result.ResultActivity;
import com.tilek.widjets.CustomLinearLayoutManager;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements OnBtnClickListener, View.OnClickListener {

    ActivityQuestionBinding binding;
    QuizAdapter quizAdapter;
    QuestionViewModel questionViewModel;
    int position;
    int clickedPosition;

    public static final String CORRECT_ANSWERS = "correct_answers";
    public static final String QUESTIONS = "questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_question);

        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);

        binding.setViewModel(questionViewModel);

        recyclerInit();
        getQuestions();

        lastQuestion();

        binding.skipBtn.setOnClickListener(this);

        binding.horizontalRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                CustomLinearLayoutManager linearLayoutManager = (CustomLinearLayoutManager) recyclerView.getLayoutManager();
                position = linearLayoutManager.findFirstVisibleItemPosition();
                binding.setPosition(position + 1);
                binding.progressTv.setText(String.valueOf(position + 1) + "/" + String.valueOf(questionViewModel.mQuestion.size()));
                binding.typeOfQuestionTv.setText(questionViewModel.categories.get(position));

            }
        });
    }

    private void lastQuestion() {
        questionViewModel.isLast.observe(this, correctA -> {
            Intent intent = new Intent(QuestionActivity.this,ResultActivity.class);
            intent.putExtra(CORRECT_ANSWERS,correctA);
            intent.putExtra(QUESTIONS,new Gson().toJson(questionViewModel.mQuestion));
            startActivity(intent);
            finish();
        });
    }

    private void getQuestions() {
        Intent intent = getIntent();
        questionViewModel.getQuestions(intent.getIntExtra(QuizFragment.AMOUNT, 10),
                intent.getIntExtra(QuizFragment.ID, 9),
                intent.getStringExtra(QuizFragment.DIFFICULTY));
        questionViewModel.questionLiveData.observe(this, new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                quizAdapter.setQuestions(questions);
                binding.progressBar.setMax(questions.size());
                binding.typeOfQuestionTv.setText(questions.get(0).getCategory());
            }
        });
    }


    private void recyclerInit() {
        quizAdapter = new QuizAdapter();
        quizAdapter.setListener(this);
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.horizontalRecycler.setLayoutManager(layoutManager);
        binding.horizontalRecycler.setAdapter(quizAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        binding.setPosition(1);
        snapHelper.attachToRecyclerView(binding.horizontalRecycler);

    }

    @Override
    public void onClick(int position, int answerPosition,Button button) {
        button.setBackgroundResource(R.drawable.question_click);
        button.setTextColor(Color.WHITE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                check(position,button);
            }
        },1000);

        new Handler().postDelayed(() -> {
            questionViewModel.onAnswerClick(position,answerPosition);
            binding.horizontalRecycler.scrollToPosition(position + 1);
        },2000);

        clickedPosition = position;
        questionViewModel.moveToQuestionFinish(position);
    }

    public void check (int position, Button button){
        boolean trueS;
            if (button.getText().toString().equals(questionViewModel.mQuestion.get(position).getCorrectAnswer())){
                button.setBackgroundResource(R.drawable.question_right_back);
                button.setTextColor(Color.WHITE);
                trueS = true;
            }else{
                button.setBackgroundResource(R.drawable.question_wrong);
                button.setTextColor(Color.WHITE);
                trueS = false;
            }
            if (!trueS){
                ArrayList<Button> btns = new ArrayList<>();
                ArrayList<Button> btnsBoolean = new ArrayList<>();
                btns.add(quizAdapter.getHolder1().questionHolderBinding.btnOne);
                btns.add(quizAdapter.getHolder1().questionHolderBinding.btnTwo);
                btns.add(quizAdapter.getHolder1().questionHolderBinding.btnThree);
                btns.add(quizAdapter.getHolder1().questionHolderBinding.btnFour);
                btnsBoolean.add(quizAdapter.getHolder1().questionHolderBinding.btnYesBoolean);
                btnsBoolean.add(quizAdapter.getHolder1().questionHolderBinding.btnNoBoolean);
                for (int i = 0; i < btns.size(); i++) {
                    if (btns.get(i).getText().toString().equals(questionViewModel.mQuestion.get(position).getCorrectAnswer())){
                        btns.get(i).setBackgroundResource(R.drawable.question_right_back);
                        btns.get(i).setTextColor(Color.WHITE);
                    }
                }
                for (int i = 0; i < btnsBoolean.size(); i++) {
                    if (btnsBoolean.get(i).getText().toString().equals(questionViewModel.mQuestion.get(position).getCorrectAnswer())){
                        btnsBoolean.get(i).setBackgroundResource(R.drawable.question_right_back);
                        btnsBoolean.get(i).setTextColor(Color.WHITE);
                    }
                }
            }
    }

    @Override
    public void onBackPressed() {
        if (position > 0){
            binding.horizontalRecycler.scrollToPosition(position - 1);
        }else {
        super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (position > clickedPosition){
            questionViewModel.skip(position);
            binding.horizontalRecycler.scrollToPosition(position + 1);
        }else{
            binding.horizontalRecycler.scrollToPosition(position + 1);
        }
    }
}