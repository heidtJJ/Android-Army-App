package com.example.android.armyapp;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuizHandler {
    // Module and topic for the questions to ask.
    private String module;
    private String topic;

    // Number of questions that will be asked during the quiz.
    private Integer numberOfQuestionsRequested;

    // This number is the total number of questions in the database. It is implied that the number of questions equals the number of answers.
    public static final int NUMBER_OF_BUTTONS = 4;

    // This iterator tracks the place of the current question asked.
    private int questionItr = 0;

    // Lists used to keep information with questions.
    private List<String> questionsList = new ArrayList<>();
    private List<String> answersList = new ArrayList<>();

    // questionsIndexSet maps a module number to a list of questions to ask in that module.
    private HashMap<Integer, HashSet<Integer>> moduleQuestionMap = new HashMap<>();
    private Iterator<Map.Entry<Integer, HashSet<Integer>>> mapIterator;
    private Iterator<Integer> questionIterator;

    private TextView questionTextView;
    private RadioGroup radioGroup;
    private Button submitButton;
    private TextView fractionCorrectField;
    private ProgressBar quizProgressBar;

    private int correctAnswerID = -1;
    private boolean firstQuestionLoaded = false;

    private Integer currentCategory = -1;

    // Error messages

    /**
     * This class will first retrieve a list of random question indices.
     *
     * @param module
     * @param topic
     */
    public QuizHandler(String module, String topic, int numberOfQuestionsRequested_, TextView questionTextView,
                       RadioGroup radioGroup_, Button submitButton_, TextView fractionCorrectField_, ProgressBar quizProgressBar_) {
        // Set topic and module class members.
        this.module = module;
        this.topic = topic;
        this.questionTextView = questionTextView;
        this.radioGroup = radioGroup_;
        this.submitButton = submitButton_;
        this.fractionCorrectField = fractionCorrectField_;
        this.quizProgressBar = quizProgressBar_;

        // The number of questions requested is the number of multiple choice q's (currently 5)
        this.numberOfQuestionsRequested = numberOfQuestionsRequested_;

        // Get categories and question indices
        FirebaseDatabase.getInstance().getReference("/quizzes/" + topic + "_counts/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long numCategories = dataSnapshot.getChildrenCount();

                Random rand = new Random();
                int questionCounter = 0;
                if (numberOfQuestionsRequested <= numCategories) {
                    // no duplicate categories asked
                    while (questionCounter < numberOfQuestionsRequested) {
                        int moduleNumber = rand.nextInt((int) numCategories);

                        // Check if the module number already appears in map.
                        if (!moduleQuestionMap.containsKey(moduleNumber)) {
                            HashSet<Integer> questionIndexList = new HashSet<>();
                            questionIndexList.add(rand.nextInt(((Long) dataSnapshot.child(String.valueOf(moduleNumber)).getValue()).intValue()));
                            moduleQuestionMap.put(moduleNumber, questionIndexList);
                            ++questionCounter;
                        }
                    }
                } else {
                    // duplicate categories will be asked. Does not ask an equal (fair) number of
                    // questions from each category. Selection of each question is random.
                    while (questionCounter < numberOfQuestionsRequested) {
                        int moduleNumber = rand.nextInt(((int) numCategories));

                        // Check if the module number already appears in map.
                        if (!moduleQuestionMap.containsKey(moduleNumber)) {
                            HashSet<Integer> questionIndexList = new HashSet<>();
                            moduleQuestionMap.put(moduleNumber, questionIndexList);
                        }
                        int questionIndexToAsk = rand.nextInt(((Long) dataSnapshot.child(String.valueOf(moduleNumber)).getValue()).intValue());
                        if (!moduleQuestionMap.get(moduleNumber).contains(questionIndexToAsk)) {
                            moduleQuestionMap.get(moduleNumber).add(questionIndexToAsk);
                            ++questionCounter;
                        }
                    }
                }

                // Map is now set up.
                // Set up iterators.
                mapIterator = moduleQuestionMap.entrySet().iterator();
                Map.Entry<Integer, HashSet<Integer>> firstEntry = mapIterator.next();
                currentCategory = firstEntry.getKey();
                questionIterator = firstEntry.getValue().iterator();
                setUpNewQuestion();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("ERROR");
            }

        });
    }

    public void setUpNewQuestion() {
        if (!questionIterator.hasNext()) {
            if (!mapIterator.hasNext()) {
                return;
            } else {
                Map.Entry<Integer, HashSet<Integer>> nextEntry = mapIterator.next();
                currentCategory = nextEntry.getKey();
                questionIterator = nextEntry.getValue().iterator();
            }
        }
        final int questionIndex = questionIterator.next();

        // Set up question.
        FirebaseDatabase.getInstance().getReference("/quizzes/" + topic + "_q/" + String.valueOf(currentCategory) + "/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the question from the database, and set the question. av for army values
                String question = (String) dataSnapshot.child(Integer.toString(questionIndex)).getValue();
                questionTextView.setText(question);
                questionsList.add(question);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("ERROR");
            }
        });

        // Set up answers from database.
        FirebaseDatabase.getInstance().getReference("/quizzes/" + topic + "_a/" + String.valueOf(currentCategory) + "/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Retrieve the answers from the database.
                String correctAnswer = (String) dataSnapshot.child(Integer.toString(questionIndex)).getValue();

                // Generate the indices of the questions to pull from the database.
                Set<Integer> potentialAnswerIndexSet = new HashSet<>();
                potentialAnswerIndexSet.add(questionIndex);

                // Create a set for the indices used for the radio buttons.
                Set<Integer> questionIndexTracker = new HashSet<>();

                // Set the correct answer in the right button.
                Random rand = new Random();
                int indexOfButtonToSet = rand.nextInt(NUMBER_OF_BUTTONS);
                ((RadioButton) radioGroup.getChildAt(indexOfButtonToSet)).setText(correctAnswer);
                answersList.add(correctAnswer);

                // Save this ID of the radio button for future use when checking the answers.
                correctAnswerID = ((RadioButton) radioGroup.getChildAt(indexOfButtonToSet)).getId();
                questionIndexTracker.add(indexOfButtonToSet);

                while (questionIndexTracker.size() < NUMBER_OF_BUTTONS) {
                    // Get the index of the next button to set
                    indexOfButtonToSet = rand.nextInt(NUMBER_OF_BUTTONS);

                    // Check if the button was already set.
                    if (!questionIndexTracker.contains(indexOfButtonToSet)) {
                        // Pull an unused answer from the database.
                        int indexOfAnswerFromDatabase = rand.nextInt((int) dataSnapshot.getChildrenCount());

                        // Check if this answer from the database was already set.
                        if (!potentialAnswerIndexSet.contains(indexOfAnswerFromDatabase)) {
                            // Pull this answer from the database.
                            String wrongAnswer = (String) dataSnapshot.child(Integer.toString(indexOfAnswerFromDatabase)).getValue();

                            System.out.println("wrongAnswer: " + wrongAnswer);

                            // Toast.makeText(radioGroup.getContext(), wrongAnswer + " " + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();


                            // Set the answer and add to the previously used indices in the database.
                            ((RadioButton) radioGroup.getChildAt(indexOfButtonToSet)).setText(wrongAnswer);
                            questionIndexTracker.add(indexOfButtonToSet);
                            potentialAnswerIndexSet.add(indexOfAnswerFromDatabase);
                        }
                    }

                }
                if (!firstQuestionLoaded) {
                    firstQuestionLoaded = true;
                    quizProgressBar.setVisibility(View.GONE);
                    quizProgressBar = null;// for memory management

                    radioGroup.setVisibility(View.VISIBLE);

                    // Used once in this object.
                    submitButton.setVisibility(View.VISIBLE);
                    // submitButton = null;
                    fractionCorrectField.setVisibility(View.VISIBLE);
                    // fractionCorrectField = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("ERROR");
            }
        });
        questionItr++;
    }

    public boolean checkCorrectAnswer() {
        return correctAnswerID == radioGroup.getCheckedRadioButtonId();
    }

    public List<String> getQuestionsList() {
        return this.questionsList;
    }

    public List<String> getAnswersList() {
        return this.answersList;
    }
}
