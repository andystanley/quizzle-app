package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.view.View;

public class HighScoreActivity extends Activity {

    String quizId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        TextView textScore = findViewById(R.id.your_score);
        TextView highScore = findViewById(R.id.high_score);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score",0);
        quizId = intent.getStringExtra("quizId");
        textScore.setText("Your Score: " + score);

        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore",0);
        if(highscore>= score)
            highScore.setText("High score: "+highscore);
        else
        {
            highScore.setText("New highscore: "+score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }
    }

    public void onRepeatClick(View view) {
        Intent quizActivity = new Intent(HighScoreActivity.this, QuizActivity.class);
        quizActivity.putExtra("quizId", quizId);
        startActivity(quizActivity);
    }
}

