package com.bignerdranch.android.geoquizv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.geoquizv2.R;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity345";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;
    private TextView questionTextView;

    private com.bignerdranch.android.geoquizv2.Question[] questionBank = new com.bignerdranch.android.geoquizv2.Question[]{
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_australia, true),
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_oceans, true),
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_mideast, false),
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_africa, false),
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_americas, true),
            new com.bignerdranch.android.geoquizv2.Question(R.string.question_asia, true),
    };

    private boolean[] cheatQuestionBank = new boolean[questionBank.length];

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            cheatQuestionBank = savedInstanceState.getBooleanArray("cheater");
        }

        questionTextView = findViewById(R.id.quest_text);
        int question = questionBank[currentIndex].getTextId();
        questionTextView.setText(question);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questionBank.length;
                updateQuestion();
            }
        });

        cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = questionBank[currentIndex].isTrueAnswer();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        updateQuestion();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        savedInstanceState.putBooleanArray("cheater", cheatQuestionBank);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = questionBank[currentIndex].getTextId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean pressed){
        int messageResId = 0;

        if (cheatQuestionBank[currentIndex]){
            messageResId = R.string.judgment_toast;
        } else {
            boolean realAnswer = questionBank[currentIndex].isTrueAnswer();

            if (pressed == realAnswer) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            cheatQuestionBank[currentIndex] = CheatActivity.wasAnswerShown(data);
        }
    }
}
