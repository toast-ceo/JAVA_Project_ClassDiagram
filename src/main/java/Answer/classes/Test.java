package Answer.classes;

import Answer.Interface.Score;
import Answer.classes.Solve;

import java.util.List;

abstract public class Test {
    //클라이언트 클래스

    String name;
    Score score;
    Solve solve;
    String tempAnswer;

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getScore(String userName, String putAnswer, String userAnswer, String tmKey) {
        score.scoreGrading(userName, putAnswer, userAnswer, tmKey);
    }

    public void addScore(Score score) {
        this.score = score;
    }

    public String getSolve(String quiz) {
        this.tempAnswer = this.solve.solveStrategy(quiz);
        return tempAnswer;
    }

    public String getSolve(String quiz, List arrayList) {
        this.tempAnswer = this.solve.solveStrategy(quiz, arrayList);
        return tempAnswer;
    }

    public void addSolve(Solve solve) {
        this.solve = solve;
    }


}
