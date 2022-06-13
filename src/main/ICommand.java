package main;

import java.io.Serializable;

/**
 * Command interface used to create commands that interact with the game.
 * @author Alexandr Belcenko (bela08), based on IPrikaz by Jarmila Pavlickova
 * @version 1.0
 */
public interface ICommand extends Serializable {

    /**
     * Intended to get keyword from a Command.
     * @return keyword
     */
    String getKeyword();

    /**
     * Executes the command based on parameters.
     * @param parameter String fed to the command
     */
    void execute(String parameter);

}
