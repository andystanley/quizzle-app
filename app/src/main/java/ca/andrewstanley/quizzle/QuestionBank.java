package ca.andrewstanley.quizzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by mobee on 2017-12-30.
 */

public class QuestionBank {
    //Declare list of question Objects
    List<Questions> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

    //returns number of questions in list
    public int getLength() { return list.size(); }

    //method returns question from list based on list index
    public String getQuestion(int a)
    { return list.get(a).getQuestion();}

    //method returns single mc item based on list index
    //based on number of multiple choice items in list (4)
    //1,2,3,4 as argument
    public String getChoice(int index , int num)
    { return list.get(index).getChoice(num - 1);}

    //returns correct answer
    public String getCorrectAnswer(int a)
    { return list.get(a).getAnswer();}

    public void initQuestions(Context context){
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from db

        if(list.isEmpty()){
            myDataBaseHelper.addInitialQuestion(new Questions("1. How are integers declared?", new String[]{"integer","float","int","double"}, "int"));
            myDataBaseHelper.addInitialQuestion(new Questions("2. What is an Activity in Android?", new String[]{"Performs the actions on screen","Manage Application Content","Screen UI","None of the above"}, "Performs the actions on screen"));
            myDataBaseHelper.addInitialQuestion(new Questions("3. What is ADB in android?", new String[]{"Image Tool","Development Tool","Android Debug Bridge","None of the above"}, "Android Debug Bridge"));
            myDataBaseHelper.addInitialQuestion(new Questions("4. In which technique, can we refresh the dynamic content in android?", new String[]{"Java","Android","Ajax","None of the above"}, "Ajax"));

            list = myDataBaseHelper.getAllQuestionsList();//get questions from database again
        }
    }
}
