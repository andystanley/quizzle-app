package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends Activity {

    private QuestionBank mQuestionLibrary = new QuestionBank();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //setup screen

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.quiz_question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();
        updateScore(mScore);

    }

    private void updateQuestion() {
        //check to make sure its in array length
        if (mQuestionNumber < mQuestionLibrary.getLength()){
            //set text for new question
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber,1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber,2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber,3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            //Toast.makeText(QuizActivity.this,"There are no more questions!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this,high_score_activity.class);
            intent.putExtra("score",mScore);
            startActivity(intent);
        }
    }

    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/"+mQuestionLibrary.getLength());
    }

    public void onClick(View view){
        //logic for all answers in one method
        Button answer = (Button) view;

        //if answer correct, increase score
        if(answer.getText().equals(mAnswer)){
            mScore = mScore + 1;
            Toast.makeText(QuizActivity.this,"Correct!",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(QuizActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();

        //show total score for user
        updateScore(mScore);
        //move onto next question once answered
        updateQuestion();
    }
}
