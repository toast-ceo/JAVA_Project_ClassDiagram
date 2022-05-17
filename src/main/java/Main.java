import Analyze.AnalyzeLogic;
import Answer.AnswerLogic;
import BasicFuntions.BasicFunctions;
import BasicFuntions.JarPathName;
import Quiz.QuizLogic;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuizLogic ql = new QuizLogic();
        AnswerLogic al = new AnswerLogic();
        AnalyzeLogic analyzeLogic = new AnalyzeLogic();
        String menu;
        JarPathName.getPathName();
        do {
            System.out.println("-------- 성경 퀴즈 프로그램 --------");
            System.out.println("기능 입력 | 1. 문제 입력 2. 문제 맞추기 3. 분석 4. 경로설정하기 0: 종료하기");
            menu = sc.next();
            System.out.println();
            if (menu.equals("1")) {
                ql.quizLogic();
                BasicFunctions.clearConsole();
            } else if (menu.equals("2")) {
                al.answerLogic();
                BasicFunctions.clearConsole();
            } else if (menu.equals("3")) {
                analyzeLogic.AnalyzeLogic();
                BasicFunctions.clearConsole();
            } else if (menu.equals("4")) {
                JarPathName.getPathName();
            } else if (menu.equals("0")) {
                System.out.println("종료");
            } else {
                System.out.println("---- 잘못 입력하셨습니다 ----");
            }
        } while (!menu.equals("0"));
    }
}
