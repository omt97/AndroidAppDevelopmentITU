package com.bignerdranch.android.geoquizv2;

public class Question {
    private int textId;
    private boolean trueAnswer;
    //private boolean hasCheated;

    public Question(int textId, boolean trueAnswer) {
        this.textId = textId;
        this.trueAnswer = trueAnswer;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}
