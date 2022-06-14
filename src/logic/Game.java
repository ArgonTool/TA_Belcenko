package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Implements Serializable.
 * Class to handle incoming commands.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Game implements Serializable {

    private final Map<String, ICommand> allCommands;

    private boolean end;
    private boolean success;

    /**
     * Constructor, creates a new HashMap for commands.
     */
    public Game() {
        this.allCommands = new HashMap<>();
        this.end = false;
        this.success = false;
    }

    /**
     * Adds a commands to the collective map, key is the keyword returned by the command.
     * @param command command to be added
     */
    public void addCommand(ICommand command) {
        allCommands.put(command.getKeyword(), command);
    }

    /**
     * Handles incoming commands.
     * Checks if the first part of a received String matches any keywords of current commands.
     * Checks if any parameters are sent along, if not sends a null value to the commands. This doesn't matter to all commands but move which handles it.
     * @param s String of text sent from the user
     */
    public void handleCommand(String s) {
        if (s.equals("")) {
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

    public boolean ended() {
        return end;
    }

    public void setEnd() {
        this.end = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess() {
        this.success = true;
    }
}
