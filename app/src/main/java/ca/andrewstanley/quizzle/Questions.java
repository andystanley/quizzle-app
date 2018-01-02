package ca.andrewstanley.quizzle;

import android.os.Bundle;

/**
 * Created by mobee on 2017-12-30.
 */

public class Questions {
    private String question;
    private String[] choice = new String[4];
    private String answer;

    public Questions() {

    }

    public Questions(String question, String[] choice, String answer) {
        this.question = question;
        this.choice[0] = choice[0];
        this.choice[1] = choice[1];
        this.choice[2] = choice[2];
        this.choice[3] = choice[3];
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoice(int i) {
        return choice[i];
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChoice(int i, String choice) {
        this.choice[i] = choice;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
