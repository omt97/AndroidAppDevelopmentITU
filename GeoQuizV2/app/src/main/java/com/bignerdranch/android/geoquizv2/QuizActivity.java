package com.bignerdranch.android.geoquizv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.geoquizv2.R;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private com.bignerdranch.android.geoquiz.Question[] questionBank = new com.bignerdranch.android.geoquiz.Question[]{
            new com.bignerdranch.android.geoquiz.Question(R.string.question_australia, true),
            new com.bignerdranch.android.geoquiz.Question(R.string.question_oceans, true),
            new com.bignerdranch.android.geoquiz.Question(R.string.question_mideast, false),
            new com.bignerdranch.android.geoquiz.Question(R.string.question_africa, false),
            new com.bignerdranch.android.geoquiz.Question(R.string.question_americas, true),
            new com.bignerdranch.android.geoquiz.Question(R.string.question_asia, true),
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
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
        boolean realAnswer = questionBank[currentIndex].isTrueAnswer();

        int messageResId = 0;

        if (pressed == realAnswer){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
