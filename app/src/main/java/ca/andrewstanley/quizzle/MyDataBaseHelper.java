package ca.andrewstanley.quizzle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mobee on 2017-12-30.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    private  SQLiteOpenHelper openHelper;

    //database title
    public static final String DATABASE_QUIZ = "questionBanks.db";

    //current version of database
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_QUESTION_BANK = "QuestionBank";

    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";

    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTION_BANK + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION + " TEXT, "
            + CHOICE1 + " TEXT, " + CHOICE2 + " TEXT, " + CHOICE3 + " TEXT, "
            + CHOICE4 + " TEXT, " + ANSWER + " TEXT)";

    public MyDataBaseHelper(Context context) {
        super(context, TABLE_QUESTION_BANK, null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUESTION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_BANK);
        onCreate(sqLiteDatabase);
    }


    public long addInitialQuestion(Questions question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(CHOICE1, question.getChoice(0));
        values.put(CHOICE2, question.getChoice(1));
        values.put(CHOICE3, question.getChoice(2));
        values.put(CHOICE4, question.getChoice(3));
        values.put(ANSWER, question.getAnswer());

        long insert = db.insert(TABLE_QUESTION_BANK, null, values);
        return insert;
    }

    /**
     * Extract data from database and save it in ArrayList of data Type Question
     */

    public List<Questions> getAllQuestionsList() {
        List<Questions> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUESTION_BANK;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curs = db.rawQuery(selectQuery, null);

        if (curs.moveToFirst()) {
            do {
                Questions question = new Questions();

                String questText = curs.getString(curs.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String choice1Text = curs.getString(curs.getColumnIndex(CHOICE1));
                question.setChoice(0, choice1Text);

                String choice2Text = curs.getString(curs.getColumnIndex(CHOICE2));
                question.setChoice(1, choice2Text);

                String choice3Text = curs.getString(curs.getColumnIndex(CHOICE3));
                question.setChoice(2, choice3Text);

                String choice4Text = curs.getString(curs.getColumnIndex(CHOICE4));
                question.setChoice(3, choice4Text);

                String answerText = curs.getString(curs.getColumnIndex(ANSWER));
                question.setAnswer(answerText);

                //adding to Questions list
                questionArrayList.add(question);
            } while (curs.moveToNext());
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }
}