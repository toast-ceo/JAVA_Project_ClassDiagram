package Analyze;

import BasicFuntions.BasicFunctions;

import java.util.Scanner;

public class AnalyzeLogic {
    WrongQuizNote wrongQuizNote = new WrongQuizNote();
    CheckGrades checkGrades = new CheckGrades();

    public void AnalyzeLogic() {
        Command quizOnCommand = new QuizOnCommand(wrongQuizNote);
        Command gradesonCommand = new GradesOnCommand(checkGrades);
        AnalyzeCommand analyzeCommand = new AnalyzeCommand();
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("사용할 분석 기능을 선택하세요. 1 : 오답노트 2 : 점수현황 0 : 나가기");
            String select;
            select = sc.next();

            if (select.equals("1")) {
                analyzeCommand.setCommand(quizOnCommand);
                analyzeCommand.startAnalyze();
                continue;
            } else if (select.equals("2")) {
                analyzeCommand.setCommand(gradesonCommand);
                analyzeCommand.startAnalyze();
                continue;
            } else if (select.equals("0")) {
                BasicFunctions.clearConsole();
                break;
            } else {
                System.out.println("잘못 입력했어요!");
            }
        } while (true);
    }
}