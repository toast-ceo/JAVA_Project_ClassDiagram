package Answer.classes;

import java.util.List;
import java.util.Scanner;

public class VariousSolve extends Solve {
    //객관식푸는 클래스
    Scanner src = new Scanner(System.in);
    String select;


    @Override
    public String solveStrategy(String quiz) {
        return "잘못된 타입이에요! : Solve";
    }

    public String solveStrategy(String quiz, List arrayList) {
        String answer;
        do {

            System.out.println("문제 : " + quiz);
            System.out.print("정답을 입력하세요 (해당 보기중에 답을 입력하세요) : ");
            System.out.println("객관식 " + arrayList);
            answer = src.next();
            System.out.println(answer);
            do {
                System.out.println("입력한 답이 맞나요? A: 예 B: 다시입력하기 ");
                select = src.next();
                if (select.equals("A")) {
                    System.out.println("입력 되었습니다!");
                } else if (select.equals("B")) {
                    System.out.println("다시 입력하기!");
                } else {
                    System.out.println("잘못 입력했어요!");
                }
            } while (!select.equals("A") && !select.equals("B"));
        } while (!select.equals("A"));
        return answer;
    }
}
