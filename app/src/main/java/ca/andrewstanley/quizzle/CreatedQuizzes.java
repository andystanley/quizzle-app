package ca.andrewstanley.quizzle;


public class CreatedQuizzes {
    private String quizId;

    public CreatedQuizzes(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizId() { return quizId; }

    public void setQuizId(String quizId) { this.quizId = quizId; }
}
