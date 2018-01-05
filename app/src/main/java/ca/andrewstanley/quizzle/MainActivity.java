package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    Button btnStartQuiz;
    Button btnCreateQuiz;
    TextView txtScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views
        btnStartQuiz = findViewById(R.id.btn_start_quiz);
        btnCreateQuiz = findViewById(R.id.btn_create_quiz);
        txtScores = findViewById(R.id.txt_scores);

        // Display the recent quizzes
        displayRecentQuizzes();

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCategories();
            }
        });

        btnCreateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizCreatorActivity = new Intent(getApplicationContext(), QuizCreatorActivity.class);
                // Pass the selected quiz to the QuizActivity
                startActivity(quizCreatorActivity);
            }
        });
    }

    private void displayCategories() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a Category")
                .setIcon(R.mipmap.ic_launcher_round)
                .setItems(categories, categoryListener)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private void displayQuizzes(String [] quizzes) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a Quiz")
                .setIcon(R.mipmap.ic_launcher_round)
                .setItems(quizzes, quizListener)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private String[] categories = {"Computer Science", "Geography", "Biology", "Custom Quizzes"};

    private String[] quizzes = new String []{null};

    DialogInterface.OnClickListener categoryListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //Toast.makeText(MainActivity.this, "You selected " + itemList[which], Toast.LENGTH_LONG).show();

            if (which == 0) { // Computer Science
                quizzes = new String[]{"Android", "C#", "C++"};
            }
            else if (which == 1) { // Geography
                quizzes = new String[]{"Canada"};
            }
            else if (which == 2) { // Biology
                quizzes = new String[]{"Osmosis"};
            }
            else if (which == 3) { // Created Quizzes
                QuizDb database = new QuizDb(getApplicationContext());
                quizzes = database.getCreatedQuizzes(); //get questions/choices/answers from db
            }
            displayQuizzes(quizzes);
        }
    };

    DialogInterface.OnClickListener quizListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "You selected " + quizzes[which], Toast.LENGTH_LONG).show();

            // Open the QuizActivity
            Intent quizActivity = new Intent(getApplicationContext(), QuizActivity.class);
            // Pass the selected quiz to the QuizActivity
            quizActivity.putExtra("quizId", quizzes[which]);
            startActivity(quizActivity);
        }
    };

    public void displayRecentQuizzes() {
        SharedPreferences scores = PreferenceManager.getDefaultSharedPreferences(this);

        StringBuilder scoresBuilder = new StringBuilder("");

        scoresBuilder.append("Quiz: ");
        scoresBuilder.append(scores.getString("quiz", ""));
        scoresBuilder.append(" Score: ");
        scoresBuilder.append(scores.getInt("score", 0));

        txtScores.setText(scoresBuilder);
    }
}
