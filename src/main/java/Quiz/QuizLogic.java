package Quiz;

import Quiz.Classes.HumanQuizFactory;
import Quiz.Classes.NumberQuizFactory;
import Quiz.Classes.PlaceQuizFactory;
import Quiz.Interface.LogicInterface;
import Quiz.Interface.QuizFactory;

import java.util.Scanner;

public class QuizLogic {
    Scanner sc = new Scanner(System.in);
    // 문제 팩토리를 받는 변수
    QuizFactory quizFactory;
    // 퀴즈를 받는 변수
    LogicInterface quiz;

    //추상 팩토리 패턴을 불러오는 로직
    public void quizLogic() {
        String themeType;
        String answerType = null;
        //테마와 타입을 설정해주는 부분
        do {
            do {
                System.out.println("문제 테마을 선택하세요!");
                System.out.println("A : 사람 관련 B : 장소 관련 C : 숫자 관련 0 : 나가기");
                themeType = sc.next();
                if (themeType.equals("A") || themeType.equals("B") || themeType.equals("C") || themeType.equals("0")) {
                    System.out.println("입력되었습니다!");
                } else {
                    System.out.println("잘못 입력했습니다!");
                }
            } while (!themeType.equals("A") && !themeType.equals("B") && !themeType.equals("C") && !themeType.equals("0"));
            if (!themeType.equals("0")) {
                do {
                    System.out.println("문제 유형을 선택하세요!");
                    System.out.println("A : 객관식 B : 단답식 C : 주관식 ");
                    answerType = sc.next();
                    if (answerType.equals("A") || answerType.equals("B") || answerType.equals("C")) {
                        System.out.println("입력 되었습니다!");
                    } else {
                        System.out.println("잘못 입력했습니다!");
                    }
                } while (!answerType.equals("A") && !answerType.equals("B") && !answerType.equals("C"));
            } else {
                System.out.println("나가기!");
            }

            // LogicInterface 변수에 각 타입에 맞는 인스턴스로 할당해주는 부분
            if (themeType.equals("A") && answerType.equals("A") || themeType.equals("A") && answerType.equals("B")) {
                System.out.println("사람 단일 문제");
                quizFactory = new HumanQuizFactory();
                quiz = quizFactory.createOneAnswerQuiz();
                quiz.quizAlgorithm();
            } else if (themeType.equals("A") && answerType.equals("C")) {
                System.out.println("사람 복수 문제");
                quizFactory = new HumanQuizFactory();
                quiz = quizFactory.createVariousAnswerQuiz();
                quiz.quizAlgorithm();
            } else if (themeType.equals("B") && answerType.equals("A") || themeType.equals("A") && answerType.equals("B")) {
                System.out.println("장소 단일 문제");
                quizFactory = new PlaceQuizFactory();
                quiz = quizFactory.createOneAnswerQuiz();
                quiz.quizAlgorithm();
            } else if (themeType.equals("B") && answerType.equals("C")) {
                System.out.println("장소 복수 문제");
                quizFactory = new PlaceQuizFactory();
                quiz = quizFactory.createVariousAnswerQuiz();
                quiz.quizAlgorithm();
            } else if (themeType.equals("C") && answerType.equals("A") || themeType.equals("A") && answerType.equals("B")) {
                System.out.println("숫자 단일 문제");
                quizFactory = new NumberQuizFactory();
                quiz = quizFactory.createOneAnswerQuiz();
                quiz.quizAlgorithm();
            } else if (themeType.equals("C") && answerType.equals("C")) {
                System.out.println("숫자 복수 문제");
                quizFactory = new NumberQuizFactory();
                quiz = quizFactory.createVariousAnswerQuiz();
                quiz.quizAlgorithm();
            } else {
                System.out.println("해당 객체가 존재하지 않아요!");
            }
        } while (!themeType.equals("0"));
    }
}

