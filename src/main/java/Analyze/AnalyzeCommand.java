package Analyze;

public class AnalyzeCommand {
    private Command command;

    public void setCommand(Command command){
        this.command=command;
    }
    public void startAnalyze(){
        command.excute();
    }
}
