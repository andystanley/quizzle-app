package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuizCreatorActivity extends Activity {
    QuizDb dbId;
    EditText quizQuestion, answerOne, answerTwo, answerThree,answerFour, correctAnswer, addSubject;
    Button btnSaveQuestion,doneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);
        dbId = new QuizDb(this);
        quizQuestion = (EditText) findViewById(R.id.txt_question);
        answerOne = (EditText) findViewById(R.id.answer_one);
        answerTwo = (EditText) findViewById(R.id.answer_two);
        answerThree = (EditText) findViewById(R.id.answer_three);
        answerFour = (EditText) findViewById(R.id.answer_four);
        correctAnswer = (EditText) findViewById(R.id.correct_answer);
        addSubject = (EditText) findViewById(R.id.add_subject);
        btnSaveQuestion = (Button) findViewById(R.id.save_question);
        doneButton = (Button) findViewById(R.id.done_button);

        saveQuestion();

    }

    public void saveQuestion(){
        btnSaveQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean quizAdded =  dbId.addQuiz(
                        quizQuestion.getText().toString(),
                        answerOne.getText().toString(),
                        answerTwo.getText().toString(),
                        answerThree.getText().toString(),
                        answerFour.getText().toString(),
                        correctAnswer.getText().toString(),
                        addSubject.getText().toString());

              if (quizAdded == true){
                  Toast.makeText(QuizCreatorActivity.this,"Questions is Saved",Toast.LENGTH_SHORT).show();
              }
              else
                  Toast.makeText(QuizCreatorActivity.this,"Questions is Not Saved",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
