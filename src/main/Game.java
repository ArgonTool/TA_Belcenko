package main;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Game implements Serializable {

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
            if (input.length < 2) {
                allCommands.get(input[0]).execute(null);
            } else {
                allCommands.get(input[0]).execute(input[1]);
            }
        } else {
            System.out.println("Unrecognized command: " + input[0]);
        }
    }

}
