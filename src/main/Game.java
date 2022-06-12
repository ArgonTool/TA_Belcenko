package main;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Game {

    private Map<String, ICommand> allCommands;

    public Game() {
        this.allCommands = new HashMap<>();
    }

    public void addCommand(ICommand command) {
        allCommands.put(command.getKeyword(), command);
    }

    public void handleCommand(String s) {
        if (s == null) {
            System.out.println("No command entered");
            return;
        }
        s = s.toLowerCase(Locale.ENGLISH);
        String[] input = s.split("\\s+");
        if(allCommands.containsKey(input[0])) {
            allCommands.get(input[0]).execute(input[1]);
        }else{
            System.out.println("Unrecognized command: " + input[0]);
        }
    }

}
