package Answer.classes;

public class AnswerFacade {
    private final VariousFacade one;
    private final DescribeFacade two;
    private final ShortsFacade three;

    public AnswerFacade() {
        one = new VariousFacade();
        two = new DescribeFacade();
        three = new ShortsFacade();
    }

    public void VariousType() {
        one.VariousMethod();
    }

    public void DescribeType() {
        two.DescribeMethod();
    }

    public void ShortsType() {
        three.ShortsMethod();
    }
}
