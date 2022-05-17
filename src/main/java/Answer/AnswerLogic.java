package Answer;

//전략패턴

import Answer.classes.AnswerFacade;

import java.util.*;

public class AnswerLogic {
    public void answerLogic() {
        Scanner src = new Scanner(System.in);
        AnswerFacade answerFacade = new AnswerFacade();

        QUIZ:
        for (; ; ) {
            System.out.print("문제타입을 고르십시오: ");
            System.out.println("A: 객관식 B : 단답식 C : 서술형 0 : 나가기");
            String QuizType = src.next();//문제타입입력
            if (QuizType.equals("A")) {
                answerFacade.VariousType();
            } else if (QuizType.equals("B")) {
                answerFacade.ShortsType();
            } else if (QuizType.equals("C")) {
                answerFacade.DescribeType();
            } else if (QuizType.equals("0")) {
                break;
            } else {
                System.out.println("입력이 잘못되었어요!");
                continue QUIZ;
            }
        }
    }
}


//타입을 고르는 행위

