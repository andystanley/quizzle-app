package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private QuestionBank questionBank = new QuestionBank();

    private TextView scoreView;
    private TextView questionView;
    private Button buttonChoice1;
    private Button buttonChoice2;
    private Button buttonChoice3;
    private Button buttonChoice4;
    private Button btnNext;

    String quizId;
    private String answer;
    private int score = 0;
    private int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //setup views
        scoreView = findViewById(R.id.score);
        questionView = findViewById(R.id.quiz_question);
        buttonChoice1 = findViewById(R.id.choice1);
        buttonChoice2 = findViewById(R.id.choice2);
        buttonChoice3 = findViewById(R.id.choice3);
        buttonChoice4 = findViewById(R.id.choice4);
        btnNext = findViewById(R.id.btnNext);

        Intent mainActivity = getIntent();

        if (mainActivity != null) {
            quizId = mainActivity.getStringExtra("quizId");
        }

        questionBank.initQuestions(getApplicationContext(), quizId);
        nextQuestion();
        updateScore(score);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nextQuestion();
                // Reset the button colors
                buttonChoice1.setEnabled(true);
                buttonChoice2.setEnabled(true);
                buttonChoice3.setEnabled(true);
                buttonChoice4.setEnabled(true);
                buttonChoice1.setBackgroundResource(android.R.drawable.btn_default);
                buttonChoice2.setBackgroundResource(android.R.drawable.btn_default);
                buttonChoice3.setBackgroundResource(android.R.drawable.btn_default);
                buttonChoice4.setBackgroundResource(android.R.drawable.btn_default);
            }
        });
    }

    private void nextQuestion() {
        //check to make sure its in array length
        if (questionNumber < questionBank.getLength()){
            //set text for new question
            questionView.setText(questionBank.getQuestion(questionNumber));
            buttonChoice1.setText(questionBank.getChoice(questionNumber,1));
            buttonChoice2.setText(questionBank.getChoice(questionNumber,2));
            buttonChoice3.setText(questionBank.getChoice(questionNumber,3));
            buttonChoice4.setText(questionBank.getChoice(questionNumber,4));

            answer = questionBank.getCorrectAnswer(questionNumber);
            questionNumber++;
        }
        else {
            //Toast.makeText(QuizActivity.this,"There are no more questions!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),HighScoreActivity.class);
            intent.putExtra("score",score);
            intent.putExtra("quizId", quizId);
            startActivity(intent);
        }
    }

    private void updateScore(int point) {
        scoreView.setText(""+score+"/"+questionBank.getLength());
    }

    public void onClick(View view){
        //logic for all answers in one method
        Button selected = (Button) view;

        int colorCorrect;
        int colorIncorrect;

        if (Build.VERSION.SDK_INT < 23) {
            colorCorrect = getResources().getColor(R.color.colorGreen);
            colorIncorrect = getResources().getColor(R.color.colorRed);
        }
        else {
            colorCorrect = getResources().getColor(R.color.colorGreen);
            colorIncorrect = getResources().getColor(R.color.colorRed);
        }

        //if answer correct, increase score
        if(selected.getText().equals(answer)){
            selected.setBackgroundColor(colorCorrect);
            score = score + 1;
        }
        else {
            selected.setBackgroundColor(colorIncorrect);
        }

        buttonChoice1.setEnabled(false);
        buttonChoice2.setEnabled(false);
        buttonChoice3.setEnabled(false);
        buttonChoice4.setEnabled(false);

        //show total score for user
        updateScore(score);
    }
}
