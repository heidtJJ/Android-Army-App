package com.example.android.armyapp.Quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.android.armyapp.R;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    // Constants
    private static final String END_QUIZ_TEXT = "submit/end";
    private static final Integer NUM_QUESTIONS_TO_ASK = 10;
    private static final String MODULE = "module";
    private static final String TOPIC = "topic";
    private static final String QUESTION_TEXT = "Question: ";

    // Class Members
    private QuizHandler quizHandler;
    private String module;
    private String topic;
    private TextView questionTextView;
    private TextView fractionCorrectField;
    private RadioGroup radioGroup;
    private Button submitButton;
    private ProgressBar quizProgressBar;
    private RecyclerView quizRecyclerView;
    private RecyclerView.Adapter quizAdapter;
    private int questionCounter = 1;
    private Boolean[] correctAnswers = new Boolean[NUM_QUESTIONS_TO_ASK];

    /*
        Example array of questions is in firebase node /quizzes/ar_q
        Example array of answers is in firebase node /quizzes/ar_a
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get the module and topic of the questions to ask.
        module = getIntent().getStringExtra(MODULE);
        topic = getIntent().getStringExtra(TOPIC);

        // Set up reference to radio buttons and question text view.
        questionTextView = findViewById(R.id.questionView);
        radioGroup = findViewById(R.id.radioGroup);
        submitButton = findViewById(R.id.submitButton);
        fractionCorrectField = findViewById(R.id.fractionCorrectField);
        quizProgressBar = findViewById(R.id.quizProgressBar);

        quizRecyclerView = findViewById(R.id.quizQuestions_recycler_view);
        quizRecyclerView.setHasFixedSize(true);
        quizRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up a quiz handler to retrieve the questions and answers from.
        quizHandler = new QuizHandler(module, topic, NUM_QUESTIONS_TO_ASK, questionTextView, radioGroup, submitButton, fractionCorrectField, quizProgressBar);

        fractionCorrectField.setText(QUESTION_TEXT + questionCounter + "/" + NUM_QUESTIONS_TO_ASK.toString());
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterAnswer(v);
            }
        });
    }

    public void enterAnswer(final View view) {
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            correctAnswers[questionCounter - 1] = quizHandler.checkCorrectAnswer();

            // Check if we're on the last question.
            if (questionCounter >= NUM_QUESTIONS_TO_ASK) {
                radioGroup.setVisibility(View.VISIBLE);

                // Used once in this object.
                submitButton.setVisibility(View.VISIBLE);
                // submitButton = null;
                fractionCorrectField.setVisibility(View.VISIBLE);

                List<String> questionsList = quizHandler.getQuestionsList();
                List<String> answersList = quizHandler.getAnswersList();

                quizAdapter = new QuizQA_Adapter(questionsList, answersList, correctAnswers);
                quizRecyclerView.setAdapter(quizAdapter);

                // Make questions, answers, and submit buttons invisible.
                radioGroup.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);
                fractionCorrectField.setVisibility(View.GONE);
                questionTextView.setVisibility(View.GONE);
                ((TextView) findViewById(R.id.quizTitle)).append(" results");
                findViewById(R.id.complete_btn).setVisibility(View.VISIBLE);
                quizRecyclerView.setVisibility(View.VISIBLE);
                return;
            }

            // Set up the next question in the UI.
            quizHandler.setUpNewQuestion();
            if (++questionCounter == NUM_QUESTIONS_TO_ASK)
                ((Button) view).setText(END_QUIZ_TEXT);

            fractionCorrectField.setText(QUESTION_TEXT + questionCounter + "/" + NUM_QUESTIONS_TO_ASK.toString());
            radioGroup.clearCheck();
        } else {
            // No radio button is selected.
            Toast.makeText(getApplicationContext(), "Please select an answer.", Toast.LENGTH_SHORT).show();
        }

    }

    // Called when the quiz completed and user clicks the "click to complete quiz" button.
    public void completeQuiz(View view) {
        Toast.makeText(this, "Your quiz results were saved!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
