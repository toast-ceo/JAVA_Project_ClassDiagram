package Analyze;

import BasicFuntions.BasicFunctions;
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
import java.util.Scanner;

public class WrongQuizNote {
    boolean select;
    String inputUserName;//찾고싶은 유저이름
    List<String> tempJsonArray;
    List<String> inputJsonArray = new ArrayList<>();

    public void wrongQuizNote() {
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("찾고싶은 유저이름을 입력하세요.   0: 나가기");
            System.out.print("입력 : ");
            this.inputUserName = sc.next();
            System.out.println(inputUserName);
            if(inputUserName.equals("0")){
                BasicFunctions.clearConsole();
                break;
            }else {
                readUserInfoJson();
                if (select) {
                    sortArray();
                    showWrongQuiz();
                } else {
                    continue;
                }
            }
            select = false;
            tempJsonArray.clear();
            inputJsonArray.clear();
        } while (true);
    }

    // JSON을 읽는 메서드
    void readUserInfoJson() {
        String path = JarPathName.UserJson;
        JSONObject jsonObject;
        ArrayList<JSONObject> jsonArray;// 정답 받는 필드

        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(path);
            jsonObject = (JSONObject) jsonParser.parse(reader);
            jsonArray = (ArrayList<JSONObject>) jsonObject.get("User");

            for (int a = 0; a < jsonArray.size(); a++) {
                if (jsonArray.get(a).get("name").equals(inputUserName)) {
                    tempJsonArray = (ArrayList<String>) jsonArray.get(a).get("wrongNum");
                    select = true;
                    break;
                } else if (a < jsonArray.size() - 1) {
                    continue;
                } else {
                    System.out.println("유저 정보가 없어요!\n");

                    break;
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

    // 정렬 메서드
    void sortArray() {
        for (String item : tempJsonArray) {
            if (!inputJsonArray.contains(item))
                inputJsonArray.add(item);
        }
    }

    void showWrongQuiz() {
        String path = "";
        String placePath = JarPathName.PlaceQuizPathJson;
        String humanPath = JarPathName.HumanQuizPathJson;
        String numberPath = JarPathName.NumberQuizPathJson;


        for (int a = 0; a < inputJsonArray.size(); a++) {
            if (inputJsonArray.get(a).contains("HA")) {
                path = humanPath;
            } else if (inputJsonArray.get(a).contains("PA")) {
                path = placePath;
            } else if (inputJsonArray.get(a).contains("NA")) {
                path = numberPath;
            } else {
                System.out.println("경로가 없어요 !");
            }
            JSONObject jsonObject;
            String keyValue = inputJsonArray.get(a).substring(0, 3);
            try {
                JSONParser jsonParser = new JSONParser();
                Reader reader = new FileReader(path);
                jsonObject = (JSONObject) jsonParser.parse(reader);
                jsonObject = (JSONObject) jsonObject.get(keyValue);
                jsonObject = (JSONObject) jsonObject.get(inputJsonArray.get(a));

                System.out.println("\n---------------오답노트---------------");
                System.out.println("문제 번호 : " + keyValue);
                System.out.println("문제 :" + jsonObject.get("Quiz"));
                System.out.println("정답 : "+ jsonObject.get("QuizAnswer"));

            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
