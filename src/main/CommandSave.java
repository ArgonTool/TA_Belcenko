package main;

public class CommandSave implements ICommand{

    Game game;
    Player player;
    MyMap map;

    @Override
    public String getKeyword() {
        return "save";
    }

    @Override
    public void execute(String parameter) {

    }
}
