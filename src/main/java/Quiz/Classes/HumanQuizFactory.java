package Quiz.Classes;


import Quiz.Interface.QuizFactory;

public class HumanQuizFactory implements QuizFactory {
    public OneAnswerQuiz createOneAnswerQuiz() {
        return new HumanOneQuiz();
    }

    public VariousAnswerQuiz createVariousAnswerQuiz() {
        return new HumanVariousQuiz();
    }
}
