package Quiz.Classes;

import Quiz.Interface.LogicInterface;

public abstract class VariousAnswerQuiz implements LogicInterface {

    abstract void inputQuiz();

    abstract void inputAnswer();

    abstract void saveJson();

    public void quizAlgorithm() {
        inputQuiz();
        inputAnswer();
        saveJson();
    }

}
