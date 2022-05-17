package Quiz.Classes;

import BasicFuntions.JarPathName;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

class NumberOneQuiz extends OneAnswerQuiz {
    String question;
    String answer;
    String select;


    public void inputQuiz() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("숫자와 관련된 문제를 입력하세요");
            String q = sc.nextLine();
            this.question = q;
            System.out.println(question);
            do {
                System.out.println("입력하신 문제가 맞나요? A: 예,  B: 다시입력");
                select = sc.next();
                if (select.equals("A")) {
                    System.out.println("입력 되었습니다!");
                } else if (select.equals("B")) {
                    System.out.println("다시 입력하기!");
                } else {
                    System.out.println("잘못 입력했어요!");
                }
            } while (!select.equals("A") && !select.equals("B"));

        } while (!select.equals("A"));
    }

    public void inputAnswer() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("정답를 입력하세요");
            String a = sc.nextLine();
            this.answer = a;
            System.out.println(answer);
            do {
                System.out.println("입력하신 정답이 맞나요? A: 예,  B: 다시입력");
                select = sc.next();
                if (select.equals("A")) {
                    System.out.println("입력 되었습니다!");
                } else if (select.equals("B")) {
                    System.out.println("다시 입력하기!");
                } else {
                    System.out.println("잘못 입력했어요!");
                }
            } while (!select.equals("A") && !select.equals("B"));
        } while (!select.equals("A"));
    }

    public void saveJson() {
        Gson gson = new Gson();
        String path = JarPathName.NumberQuizPathJson;
        //전체
        JSONObject jsonObject;
        //SHA JSON 배열
        JSONObject SNAJson;
        //LHA JSON 배열
        JSONObject LNAJson;
        //정보를 넣을 JSON 파일
        JSONObject QuizInnerObject = new JSONObject();

        JSONObject writeJson = new JSONObject();
        try {
            int temp;
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(path);
            jsonObject = (JSONObject) jsonParser.parse(reader);
            SNAJson = (JSONObject) jsonObject.get("SNA");
            LNAJson = (JSONObject) jsonObject.get("LNA");
            temp = SNAJson.size();

            QuizInnerObject.put("Quiz", question);
            QuizInnerObject.put("QuizAnswer", answer);
            SNAJson.put("SNA" + temp, QuizInnerObject);

            writeJson.put("SNA", SNAJson);
            writeJson.put("LNA", LNAJson);

            FileWriter fileWriter = new FileWriter(path);
            gson.toJson(writeJson, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
