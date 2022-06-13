package main;

/**
 * Implements ICommand
 * Class is a command to exit the program
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class CommandExit implements ICommand {

    /**
     * Returns keyword to trigger the command
     * @return String "exit"
     */
    @Override
    public String getKeyword() {
        return "exit";
    }

    /**
     * Exits the program
     * @param parameter not needed here but needed for other commands
     */
    @Override
    public void execute(String parameter) {
        System.exit(0);
    }
}
