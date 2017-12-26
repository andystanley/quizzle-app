package ca.andrewstanley.quizzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnStartQuiz;
    Button btnCreateQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up views
        btnStartQuiz = findViewById(R.id.btn_start_quiz);
        btnCreateQuiz = findViewById(R.id.btn_create_quiz);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCategories();
            }
        });

        btnCreateQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void displayQuizzes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose a Quiz")
                .setIcon(R.mipmap.ic_launcher_round)
                .setItems(quizzes, quizListener)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    private String[] categories = {"Computer Science", "Geography", "Biology"};

    private String[] quizzes = {"Quiz 1", "Quiz 2"};

    DialogInterface.OnClickListener categoryListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            //Toast.makeText(MainActivity.this, "You selected " + itemList[which], Toast.LENGTH_LONG).show();
            displayQuizzes();
        }
    };

    DialogInterface.OnClickListener quizListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "You selected " + quizzes[which], Toast.LENGTH_LONG).show();
        }
    };
}
