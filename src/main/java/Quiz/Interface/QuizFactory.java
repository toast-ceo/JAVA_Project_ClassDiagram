package Quiz.Interface;

import Quiz.Classes.OneAnswerQuiz;
import Quiz.Classes.VariousAnswerQuiz;

public interface QuizFactory {
    OneAnswerQuiz createOneAnswerQuiz();
    VariousAnswerQuiz createVariousAnswerQuiz();
}
