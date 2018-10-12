package com.example.android.armyapp;

import android.widget.ImageView;
import android.widget.TextView;

public class QuizListItem {
    private ImageView questionImageView;
    private TextView answerTextView;
    private TextView questionTextView;

    QuizListItem(TextView questionTextView, TextView answerTextView, ImageView questionImage) {
        this.questionImageView = questionImageView;
        this.answerTextView = answerTextView;
        this.questionTextView = questionTextView;
    }

    public ImageView getQuestionImageView() {
        return this.questionImageView;
    }

    public TextView getAnswerTextView(){
        return this.answerTextView;
    }

    public TextView getQuestionTextView(){
        return this.questionTextView;
    }

}
