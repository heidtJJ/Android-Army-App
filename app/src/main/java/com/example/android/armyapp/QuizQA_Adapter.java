package com.example.android.armyapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class QuizQA_Adapter extends RecyclerView.Adapter<QuizQA_Adapter.ViewHolder> {
    private List<String> quizQuestions;
    private List<String> quizAnswers;
    private Boolean[] correctAnswers;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView questionTextView;
        private TextView answerTextView;
        private ImageView quizResultImage;

        public ViewHolder(View view) {
            super(view);
            questionTextView = view.findViewById(R.id.questionTextView);
            answerTextView = view.findViewById(R.id.answerTextView);
            quizResultImage = view.findViewById(R.id.quizResultImage);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public QuizQA_Adapter(List<String> quizQuestions, List<String> quizAnswers, Boolean[] correctAnswers) {
        this.quizQuestions = quizQuestions;
        this.quizAnswers = quizAnswers;
        this.correctAnswers = correctAnswers;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuizQA_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quiz_list_item, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.answerTextView.setText(this.quizAnswers.get(position));
        holder.questionTextView.setText(this.quizQuestions.get(position));
        holder.quizResultImage.setImageResource(this.correctAnswers[position] ? R.mipmap.checking : R.mipmap.x_button);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return quizAnswers.size();
    }

}