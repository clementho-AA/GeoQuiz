package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_australia, true),
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnser(true);

                disableTrueFalseButton();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnser(false);

                disableTrueFalseButton();
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setEnabled(false);  //TODO: bad design of enabling/disabling button, need to revisit
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();

                enableTrueFalseButton();
                mNextButton.setEnabled(false);
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnser(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mQuestionBank[mCurrentIndex].setUserAnswerCorrect(true);
        } else {
            messageResId = R.string.incorrect_toast;
            mQuestionBank[mCurrentIndex].setUserAnswerCorrect(false);
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        if(mCurrentIndex == (mQuestionBank.length - 1)) {
            gradeUserScore();
        }
    }

    private void disableTrueFalseButton() {
        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);
        mNextButton.setEnabled(true);
    }

    private void enableTrueFalseButton() {
        mTrueButton.setEnabled(true);
        mFalseButton.setEnabled(true);
        mNextButton.setEnabled(true);
    }

    private void gradeUserScore()
    {
        int totalGrade = 0;

        for(Question answer : mQuestionBank) {
            if(answer.isUserAnswerCorrect() == true)
                totalGrade++;
        }

        totalGrade = (int) ((totalGrade * 100.0f) / mQuestionBank.length);

        CharSequence output = "You scored " + totalGrade + "%";

        Toast toast = Toast.makeText(this, output, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
