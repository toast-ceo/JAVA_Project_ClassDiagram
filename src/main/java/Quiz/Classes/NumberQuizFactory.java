package Quiz.Classes;


import Quiz.Interface.QuizFactory;

public class NumberQuizFactory implements QuizFactory {
    public OneAnswerQuiz createOneAnswerQuiz() {
        return new NumberOneQuiz();
    }

    public VariousAnswerQuiz createVariousAnswerQuiz() {
        return new NumberVariousQuiz();
    }
}