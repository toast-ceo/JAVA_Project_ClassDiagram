package Analyze;

public class QuizOnCommand implements Command {
    private WrongQuizNote wrongQuizNote;

    public QuizOnCommand(WrongQuizNote wrongQuizNote){
        this.wrongQuizNote = wrongQuizNote;
    }
    @Override
    public void excute() {
        wrongQuizNote.wrongQuizNote();
    }
}
