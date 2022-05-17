package Answer.classes;

import BasicFuntions.JarPathName;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;
import java.util.Scanner;

class ShortsFacade {
    String userName;
    JSONObject Quiz;
    int putQuizNum;
    String putQuiz;//정답 문제 필드
    String putAnswer;// 정답 받는 필드
    String tmKey;
    String userAnswer;//사용자가 입력한 정답
    String select;

    public void ShortsMethod() {
        Scanner src = new Scanner(System.in);
        System.out.print("사용자를 입력하세요: ");
        String UserName = src.next();
        this.userName = UserName;
        Test test;
        test = new Shorts(UserName);
        test.addScore(new ShortScore());
        test.addSolve(new ShortSolve());
        System.out.println("유저이름: " + test.getName());
        do {
            readJson();
            userAnswer = test.getSolve(putQuiz);
            test.getScore(userName, putAnswer, userAnswer, tmKey);


            System.out.println("1: 문제 계속 풀기 2: 문제 그만 풀기");
            select = src.next();
            if (select.equals("1")) {
                continue;
            } else if (select.equals("2")) {
                break;
            } else {
                System.out.println("잘못 입력하셨어요!");
            }

        } while (true);
    }

    // 랜덤으로 단답식 문제를 출제하는 로직
    public void readJson() {
        // 각 문제의 파일위치
        String placePath = JarPathName.PlaceQuizPathJson;
        String HumanPath = JarPathName.HumanQuizPathJson;
        String NumberPath = JarPathName.NumberQuizPathJson;
        // 단답식을 불러올 수 있는 키값
        String placeKey = "SPA";
        String HumanKey = "SHA";
        String NumberKey = "SNA";
        // 해당 테마의 퀴즈파일에 들어있는 문제의 갯수를 담는 변수
        int temp;

        Random random = new Random();
        // 값을 랜덤으로 받기위해 시간으로 시드를 받음
        random.setSeed(System.currentTimeMillis());
        // 타입(장소, 인물, 숫자) 문제를 랜덤으로 선택하기 위해 받는 변수
        int rd = random.nextInt(3);

        JSONObject jsonObject;

        // 삼항연산자를 이용하여 타입을 선택해줘서 Json을 파싱할 때 필요한 변수에 담는 부분
        String path = rd == 0 ? placePath : rd == 1 ? HumanPath : NumberPath;
        String key = rd == 0 ? placeKey : rd == 1 ? HumanKey : NumberKey;

        try {
            JSONParser jsonParser = new JSONParser();
            // 파일을 받아줌
            Reader reader = new FileReader(path);
            // JsonObject로 파싱 -> 원하는 값들을 전역변수에 담는 부분
            jsonObject = (JSONObject) jsonParser.parse(reader);
            this.Quiz = (JSONObject) jsonObject.get(key);
            temp = Quiz.size();
            this.putQuizNum = random.nextInt(temp);
            this.tmKey = key + putQuizNum;
            JSONObject input = (JSONObject) Quiz.get(tmKey);
            // 출제할 문제와 답을 전역변수에 담음
            this.putQuiz = (String) input.get("Quiz");
            this.putAnswer = (String) input.get("QuizAnswer");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
