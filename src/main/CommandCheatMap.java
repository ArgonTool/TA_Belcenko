package main;

public class CommandCheatMap implements ICommand{

    private MyMap map;

    public CommandCheatMap(MyMap map) {
        this.map = map;
    }

    @Override
    public String getKeyword() {
        return "cheat_map";
    }

    @Override
    public void execute(String parameter) {
        map.render();
    }
}
