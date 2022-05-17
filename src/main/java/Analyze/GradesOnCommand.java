package Analyze;

public class GradesOnCommand implements Command {
    private CheckGrades checkGrades;
    public GradesOnCommand(CheckGrades checkGrades){
        this.checkGrades = checkGrades;
    }
    @Override
    public void excute() {
        checkGrades.checkGrades();
    }
}
