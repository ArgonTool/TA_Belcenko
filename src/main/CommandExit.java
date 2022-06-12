package main;

public class CommandExit implements ICommand {

    @Override
    public String getKeyword() {
        return "exit";
    }

    @Override
    public void execute(String parameter) {
        System.exit(0);
    }
}
