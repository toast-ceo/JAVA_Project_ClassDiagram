package Analyze;

import BasicFuntions.JarPathName;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class CheckGrades {
    int temp; // 유저의 수의 크기를 담는 변수
    double percent; // 유저의 문제 정답률
    // 유저의 문제 정답률을 람다식으로 반환시키기 위해 씀
    BinaryOperator<Double> percentDivide = (v1, v2) -> (v1 / v2) * 100; //나누기
    ArrayList<JSONObject> jsonArray;// 정답 받는 필드

    public void checkGrades() {
        System.out.println("------------- 유저별 채점 결과 -------------");
        readJson();
        for (int a = 0; a < temp; a++) {
            // 해당 유저의 정답률을 계산
            percent = percentDivide.apply(Double.parseDouble(jsonArray.get(a).get("answerCount").toString()), Double.parseDouble(jsonArray.get(a).get("allCount").toString()));
            //유저의 이름, 맞은 갯수, 전체 갯수, 정답률을 나타내줌
            System.out.println("유저 이름 : " + jsonArray.get(a).get("name"));
            System.out.println("맞은 갯수 : " + jsonArray.get(a).get("answerCount") + "    전체 갯수 : " + jsonArray.get(a).get("allCount") + "     정답률 : " + percent + "%");
            System.out.println();
        }
    }
    // JSON파일을 읽어오는 부분
    void readJson() {
        String path = JarPathName.UserJson;
        JSONObject jsonObject;
        try {
            JSONParser jsonParser = new JSONParser();
            Reader reader = new FileReader(path);
            jsonObject = (JSONObject) jsonParser.parse(reader);
            jsonArray = (ArrayList<JSONObject>) jsonObject.get("User");
            this.temp = jsonArray.size();
            System.out.println(temp);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}