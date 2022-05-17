package Answer.classes;

import BasicFuntions.JarPathName;
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

//파서드패턴 -> 전체로직임 ㅇㅇ;;
public class VariousFacade {
    String userName;
    JSONObject Quiz;
    String putQuizNum;
    String putQuiz;//정답 문제 필드
    String putAnswer;//정답 입력 필드
    List<String> answer = new ArrayList<>();// 정답 받는 필드
    String userAnswer;
    String tmKey;
    String select;

    public void VariousMethod() {
        Scanner src = new Scanner(System.in);
        System.out.print("사용자를 입력하세요: ");
        String UserName = src.next();
        this.userName = UserName;
        Test test;
        test = new Various(UserName);
        test.addScore(new VariousScore());
        test.addSolve(new VariousSolve());
        System.out.println("유저이름: " + test.getName());
        do {
            readJson();
            userAnswer = test.getSolve(putQuiz, answer);
            test.getScore(userName, putAnswer, userAnswer, tmKey);

            System.out.println("1: 문제 계속 풀기 2: 문제 그만 풀기");
            select = src.next();
            if (select.equals("1")) {

                answer.clear();
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
        String placeKey = "SPA";
        String HumanKey = "SHA";
        String NumberKey = "SNA";
        int temp;

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        JSONObject jsonObject;
        int rd = random.nextInt(3);
        int ard = random.nextInt(4);
        String path = rd == 0 ? placePath : rd == 1 ? HumanPath : NumberPath;
        String key = rd == 0 ? placeKey : rd == 1 ? HumanKey : NumberKey;

        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(path);
            jsonObject = (JSONObject) jsonParser.parse(reader);
            this.Quiz = (JSONObject) jsonObject.get(key);
            temp = Quiz.size();
            //정답 넣어주는 부분
            for (; ; ) {
                int randomIndex = random.nextInt(temp);
                if (answer.size() == 4) {
                    break;
                } else {
                    tmKey = "" + key + randomIndex;
                    String tm1 = "QuizAnswer";
                    String tm2 = "Quiz";
                    JSONObject input = (JSONObject) Quiz.get(tmKey);
                    String answerTemp = (String) input.get(tm1);
                    String quizTemp = (String) input.get(tm2);
                    if (answer.size() == ard) {
                        this.putQuizNum = tmKey;
                        this.putQuiz = quizTemp;
                        this.putAnswer = answerTemp;
                        answer.add(answerTemp);
                    } else {
                        if (answer.contains(answerTemp)) {
                            System.out.print("");
                        } else {
                            answer.add(answerTemp);
                        }
                    }
                }
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
