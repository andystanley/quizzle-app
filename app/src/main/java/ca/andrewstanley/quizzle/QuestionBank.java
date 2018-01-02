package ca.andrewstanley.quizzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mobee on 2017-12-30.
 */

public class QuestionBank {
    //Declare list of question Objects
    List<Questions> questionList = new ArrayList<>();
    QuizDb database;

    //returns number of questions in list
    public int getLength() { return questionList.size(); }

    //method returns question from list based on list index
    public String getQuestion(int a)
    { return questionList.get(a).getQuestion();}

    //method returns single mc item based on list index
    //based on number of multiple choice items in list (4)
    //1,2,3,4 as argument
    public String getChoice(int index , int num)
    { return questionList.get(index).getChoice(num - 1);}

    //returns correct answer
    public String getCorrectAnswer(int a)
    { return questionList.get(a).getAnswer();}

    public void initQuestions(Context context, String quizId){
        database = new QuizDb(context);
        questionList = database.getQuestions(quizId);//get questions/choices/answers from db

        if(questionList.isEmpty()){
            if (quizId.equals("Android")) {
                database.saveQuestion(new Questions("1. How are integers declared?", new String[]{"integer","float","int","double"}, "int", "Android"));
                database.saveQuestion(new Questions("2. What is an Activity in Android?", new String[]{"Performs the actions on screen","Manage Application Content","Screen UI","None of the above"}, "Performs the actions on screen", "Android"));
                database.saveQuestion(new Questions("3. What is ADB in android?", new String[]{"Image Tool","Development Tool","Android Debug Bridge","None of the above"}, "Android Debug Bridge", "Android"));
                database.saveQuestion(new Questions("4. In which technique, can we refresh the dynamic content in android?", new String[]{"Java","Android","Ajax","None of the above"}, "Ajax", "Android"));
            }

            else if (quizId.equals("C#")) {
                database.saveQuestion(new Questions("1. Which of the following converts a type to a 32bit integer?", new String[]{"ToDecimal","ToDouble","ToInt16","ToInt32"}, "ToInt32", "C#"));
                database.saveQuestion(new Questions("2. Which of the following operator creates a pointer to a variable in C#?", new String[]{"sizeof","typeof","&","*"}, "*", "C#"));
            }

            else if (quizId.equals("C++")){ // Default quiz
                database.saveQuestion(new Questions("1. Which of the following converts a type to a 64bit integer?", new String[]{"ToDecimal","ToDouble","ToInt64","ToInt32"}, "ToInt64", "C++"));
                database.saveQuestion(new Questions("2. Which of the following operator DOES NOT create a pointer to a variable in C#?", new String[]{"sizeof","typeof","&","*"}, "sizeof", "C++"));
                database.saveQuestion(new Questions("3. In which technique, can we refresh the dynamic content in android?", new String[]{"Javascript","iOS","Ajax","None of the above"}, "Javascript", "C++"));
            }

            //database.saveQuestion(new Questions("1. What is the capital of Ontario", new String[]{"London","Brampton","Ajax","Toronto"}, "Toronto", "Geography"));

            questionList = database.getQuestions(quizId);//get questions from database again
        }
    }
}
