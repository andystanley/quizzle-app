package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddQuestionsActivity extends Activity {
    QuizDb dbId;
    EditText quizQuestion, answerOne, answerTwo, answerThree,answerFour, correctAnswer, addSubject;
    Button btnSaveQuestion,doneButton;
    RadioButton existingCat, newCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        //initialize all field variables
        dbId = new QuizDb(this);
        quizQuestion = findViewById(R.id.quiz_question);
        answerOne = findViewById(R.id.answer_one);
        answerTwo = findViewById(R.id.answer_two);
        answerThree = findViewById(R.id.answer_three);
        answerFour = findViewById(R.id.answer_four);
        correctAnswer = findViewById(R.id.correct_answer);
        addSubject = findViewById(R.id.add_subject);
        btnSaveQuestion = findViewById(R.id.save_question);
        doneButton = findViewById(R.id.done_button);
        final Spinner addCat = findViewById(R.id.newCatSpinner);
        existingCat = findViewById(R.id.existingCat);
        newCat = findViewById(R.id.newCat);

        String[] categories;

        existingCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (existingCat.isChecked()){
                    addSubject.setEnabled(false);
                    addCat.setEnabled(true);
                }
            }
        });

        newCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newCat.isChecked()) {
                    addCat.setEnabled(false);
                    addSubject.setEnabled(true);
                }
            }
        });

        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (existingCat.isChecked()){
                    saveQuestion(addCat.getSelectedItem().toString());
                }
                else {
                    saveQuestion(addSubject.getText().toString());
                }

            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the main activity
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

        QuizDb database = new QuizDb(getApplicationContext());
        categories = database.getAllQuizzes();
        //create instance of array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                //pass categories array
                categories
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        addCat.setAdapter(adapter);
    }

    public void saveQuestion(String quiz) {
        boolean quizAdded = dbId.addQuiz(
                quizQuestion.getText().toString(),
                answerOne.getText().toString(),
                answerTwo.getText().toString(),
                answerThree.getText().toString(),
                answerFour.getText().toString(),
                correctAnswer.getText().toString(),
                quiz);

        if (quizQuestion.getText().toString().matches("") ||
                answerOne.getText().toString().matches("") ||
                answerTwo.getText().toString().matches("") ||
                answerThree.getText().toString().matches("") ||
                answerFour.getText().toString().matches("") ||
                correctAnswer.getText().toString().matches("")) {
            Toast.makeText(this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
        }
        else if (quiz.matches("")) {
            Toast.makeText(this, "To create a new quiz, please enter a name!", Toast.LENGTH_SHORT).show();
        }
        else if (!correctAnswer.getText().toString().matches(answerOne.getText().toString()) &&
                !correctAnswer.getText().toString().matches(answerTwo.getText().toString()) &&
                !correctAnswer.getText().toString().matches(answerThree.getText().toString()) &&
                !correctAnswer.getText().toString().matches(answerFour.getText().toString())) {
            Toast.makeText(this, "The correct answer must be on of the answers!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (quizAdded == true) {
                Toast.makeText(this, "Question saved to " + quiz, Toast.LENGTH_SHORT).show();

                // Clear all the text views
                quizQuestion.setText("");
                answerOne.setText("");
                answerTwo.setText("");
                answerThree.setText("");
                answerFour.setText("");
                correctAnswer.setText("");
                addSubject.setText("");
            } else {
                Toast.makeText(this, "Question Not Saved", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
