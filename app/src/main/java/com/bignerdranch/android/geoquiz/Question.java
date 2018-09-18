package com.bignerdranch.android.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mUserAnswerCorrect = false;
    private int test;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isUserAnswerCorrect() {
        return mUserAnswerCorrect;
    }

    public void setUserAnswerCorrect(boolean userAnswerCorrect) {
        mUserAnswerCorrect = userAnswerCorrect;
    }
}
