package main;

import logic.UI;

/**
 * Main class, starts the game.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Main {
    /**
     * Creates a new UI, starts the game.
     * @param args arguments sent from the command line, not used
     */
    public static void main(String[] args){
        UI ui = new UI();
        ui.start();
    }
}
