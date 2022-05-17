package Quiz.Classes;


import Quiz.Interface.QuizFactory;

public class PlaceQuizFactory implements QuizFactory {
    public OneAnswerQuiz createOneAnswerQuiz() {
        return new PlaceOneQuiz();
    }

    public VariousAnswerQuiz createVariousAnswerQuiz() {
        return new PlaceVariousQuiz();
    }
}