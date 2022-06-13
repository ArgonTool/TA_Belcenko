package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Implements ICommand
 * Class to save a game that can be resumed later.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class CommandSave implements ICommand {

    private Save save;

    /**
     * Constructor, creates a new Save Object to store current game, player and map
     * @param game current game
     * @param player current player
     * @param map current state of the map
     */
    public CommandSave(Game game, Player player, MyMap map) {
        this.save = new Save(game, player, map);
    }

    /**
     * Returns keyword
     * @return keyword
     */
    @Override
    public String getKeyword() {
        return "save";
    }

    /**
     * Outputs the Save Object to a new File
     * @param parameter not needed here but for other commands
     */
    @Override
    public void execute(String parameter) {
        try {
            File f = new File("C:/savefiles/save");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(save);
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Problem while saving");
            e.printStackTrace();
        }
    }
}
