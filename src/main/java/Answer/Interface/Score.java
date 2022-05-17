package Answer.Interface;

//채점하는 행위
public interface Score {
    public void scoreGrading(String userName, String putAnswer, String userAnswer, String tmKey);

    public void saveJson(String check);
}
