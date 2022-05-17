package BasicFuntions;

import java.util.Scanner;

public class JarPathName {
    public static String UserJson;
    public static String HumanQuizPathJson;
    public static String PlaceQuizPathJson;
    public static String NumberQuizPathJson;

    public static void  getPathName(){
        String select;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("UserJson의 경로를 입력하세요");
            UserJson  = scanner.next();
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("HumanQuizPathJson의 경로를 입력하세요");
            HumanQuizPathJson  = scanner1.next();
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("PlaceQuizPathJson의 경로를 입력하세요");
            PlaceQuizPathJson  = scanner2.next();
            Scanner scanner3 = new Scanner(System.in);
            System.out.println("NumberQuizPathJson의 경로를 입력하세요");
            NumberQuizPathJson  = scanner3.next();

            System.out.println();
            System.out.println("1: 입력완료 | 아무키) : 다시하기");
            Scanner scanner4 = new Scanner(System.in);
            select = scanner4.next();
            if (select.equals("1")){
                BasicFunctions.clearConsole();
                break;
            }else {
                continue;
            }
        }while (true);
    }
}
