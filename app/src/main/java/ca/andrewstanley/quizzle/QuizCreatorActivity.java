package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class QuizCreatorActivity extends Activity {
    QuizDb dbId;
    EditText quizQuestion, answerOne, answerTwo, answerThree,answerFour, correctAnswer, addSubject;
    Button btnSaveQuestion,doneButton;
    RadioButton existingCat, newCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);

        //initialize all field variables
        dbId = new QuizDb(this);
        quizQuestion = (EditText) findViewById(R.id.quiz_question);
        answerOne = (EditText) findViewById(R.id.answer_one);
        answerTwo = (EditText) findViewById(R.id.answer_two);
        answerThree = (EditText) findViewById(R.id.answer_three);
        answerFour = (EditText) findViewById(R.id.answer_four);
        correctAnswer = (EditText) findViewById(R.id.correct_answer);
        addSubject = (EditText) findViewById(R.id.add_subject);
        btnSaveQuestion = (Button) findViewById(R.id.save_question);
        doneButton = (Button) findViewById(R.id.done_button);
        final Spinner addCat = findViewById(R.id.newCatSpinner);
        String[] categories = getResources().getStringArray(R.array.categories);
        existingCat = findViewById(R.id.existingCat);
        newCat = findViewById(R.id.newCat);

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
                saveQuestion();
            }
        });

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

    public void saveQuestion() {
        boolean quizAdded = dbId.addQuiz(
                quizQuestion.getText().toString(),
                answerOne.getText().toString(),
                answerTwo.getText().toString(),
                answerThree.getText().toString(),
                answerFour.getText().toString(),
                correctAnswer.getText().toString(),
                addSubject.getText().toString());

        if (quizAdded == true) {
            Toast.makeText(QuizCreatorActivity.this, "Question is Saved", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(QuizCreatorActivity.this, "Question is Not Saved", Toast.LENGTH_SHORT).show();
    }
}
