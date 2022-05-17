package Answer.classes;

import BasicFuntions.JarPathName;
import com.google.gson.JsonArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DescribeFacade {
    String userName;
    JSONObject Quiz;
    int putQuizNum;
    String putQuiz;//정답 문제 필드
    List<JsonArray> putAnswerArray = new ArrayList<>();// 정답 받는 필드
    String putAnswer = "";
    String tmKey;
    String userAnswer;
    String select;

    public void DescribeMethod() {
        Scanner src = new Scanner(System.in);
        System.out.print("사용자를 입력하세요: ");
        String UserName = src.next();
        this.userName = UserName;
        Test test = new Describe(UserName);
        test.addScore(new DescribeScore());
        test.addSolve(new DescribeSolve());
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

    public void readJson() {
        String placePath = JarPathName.PlaceQuizPathJson;
        String HumanPath = JarPathName.HumanQuizPathJson;
        String NumberPath = JarPathName.NumberQuizPathJson;
        String placeKey = "LPA";
        String HumanKey = "LHA";
        String NumberKey = "LNA";
        int temp;


        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        JSONObject jsonObject;
        int rd = random.nextInt(3);

        String path = rd == 0 ? placePath : rd == 1 ? HumanPath : NumberPath;
        String key = rd == 0 ? placeKey : rd == 1 ? HumanKey : NumberKey;

        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(path);
            jsonObject = (JSONObject) jsonParser.parse(reader);
            this.Quiz = (JSONObject) jsonObject.get(key);
            temp = Quiz.size();
            putQuizNum = random.nextInt(temp);
            tmKey = key + putQuizNum;
            JSONObject input = (JSONObject) Quiz.get(tmKey);


            putQuiz = (String) input.get("Quiz");
            putAnswerArray = (List<JsonArray>) input.get("QuizAnswer");
            System.out.println(putAnswerArray);
            for (int a = 0; a < putAnswerArray.size(); a++) {
                putAnswer += putAnswerArray.get(a) + " ";
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
