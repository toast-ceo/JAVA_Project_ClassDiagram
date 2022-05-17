package Answer.classes;

import Answer.Interface.Score;
import BasicFuntions.JarPathName;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//서술형 채점하는 클래스
public class DescribeScore implements Score {
    String userName;
    String putAnswer;
    String userAnswer;
    String tmKey;

    @Override
    public void scoreGrading(String userName, String putAnswer, String userAnswer, String tmKey) {
        this.userName = userName;
        this.putAnswer = putAnswer;
        this.userAnswer = userAnswer;
        this.tmKey = tmKey;
        List<String> userAnswerList = List.of(putAnswer.split(" "));

        System.out.println("----------------------------------");
        System.out.println(userAnswerList);
        System.out.println(userAnswer);
        for (int a = 0; a < userAnswerList.size() + 1; a++) {
            if (userAnswerList.size() == a) {
                System.out.println("맞았습니다!");

                saveJson("A");
            } else if (userAnswer.contains(userAnswerList.get(a))) {
                continue;
            } else {
                System.out.println("틀렸습니다! 정답 : " + putAnswer);
                saveJson("B");
                break;

            }
        }

    }

    public void saveJson(String check) {
        //정답을 맞았을때
        if (check.equals("A")) {
            Gson gson = new Gson();
            String path = JarPathName.UserJson;
            JSONObject jsonObject = null;
            ArrayList<JSONObject> jsonArray = null;// 정답 받는 필드

            try {
                JSONParser jsonParser = new JSONParser();
                Reader reader = new FileReader(path);
                jsonObject = (JSONObject) jsonParser.parse(reader);
                jsonArray = (ArrayList<JSONObject>) jsonObject.get("User");

                for (int a = 0; a < jsonArray.size(); a++) {
                    if (jsonArray.get(a).get("name").equals(userName)) {
                        String answerCount = jsonArray.get(a).get("answerCount").toString();
                        String allCount = jsonArray.get(a).get("allCount").toString();
                        jsonArray.get(a).put("answerCount", Integer.parseInt(answerCount) + 1);
                        jsonArray.get(a).put("allCount", Integer.parseInt(allCount) + 1);
                        jsonArray.set(a, jsonArray.get(a));
                        jsonObject.replace(((ArrayList<?>) jsonObject.get("User")).get(a), jsonArray.get(a));
                        break;
                    } else if (a < jsonArray.size() - 1) {
                        continue;
                    } else {
                        Exception e = new Exception("유저 생성");
                        throw e;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                JSONObject newUser = new JSONObject();
                JsonArray newUserArray = new JsonArray();

                newUser.put("name", userName);
                newUser.put("allCount", 1);
                newUser.put("answerCount", 1);
                newUser.put("wrongNum", newUserArray);
                jsonArray.add(newUser);
                jsonObject.put("User", jsonArray);
            }
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(path);
                gson.toJson(jsonObject, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (check.equals("B")) {
            Gson gson = new Gson();
            String path =JarPathName.UserJson;
            JSONObject jsonObject = null;
            ArrayList<JSONObject> jsonArray = null;// 정답 받는 필드
            try {
                JSONParser jsonParser = new JSONParser();
                Reader reader = new FileReader(path);
                jsonObject = (JSONObject) jsonParser.parse(reader);
                jsonArray = (ArrayList<JSONObject>) jsonObject.get("User");

                for (int a = 0; a < jsonArray.size(); a++) {
                    if (jsonArray.get(a).get("name").equals(userName)) {
                        ArrayList<String> tempJsonArray = (ArrayList<String>) jsonArray.get(a).get("wrongNum");
                        tempJsonArray.add(tmKey);
                        String allCount = jsonArray.get(a).get("allCount").toString();
                        jsonArray.get(a).put("allCount", Integer.parseInt(allCount) + 1);
                        jsonArray.get(a).put("wrongNum", tempJsonArray);
                        jsonArray.set(a, jsonArray.get(a));
                        jsonObject.replace(((ArrayList<?>) jsonObject.get("User")).get(a), jsonArray.get(a));
                        break;
                    } else if (a < jsonArray.size() - 1) {
                        continue;
                    } else {
                        Exception e = new Exception("유저 생성");
                        throw e;
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                JSONObject newUser = new JSONObject();
                JsonArray newUserArray = new JsonArray();
                newUser.put("name", userName);
                newUser.put("allCount", 1);
                newUser.put("answerCount", 0);
                newUserArray.add(tmKey);
                newUser.put("wrongNum", newUserArray);
                jsonArray.add(newUser);
                jsonObject.put("User", jsonArray);
            }
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(path);
                gson.toJson(jsonObject, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("입력이 잘못되었어요!");
        }
    }
}
