package logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class representing the user interface.
 * Runs the game, defines the game loop.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class UI {

    private Game game;
    private MyMap map;
    private Player player;
    private final File saveFile;

    /**
     * Normal Constructor
     */
    public UI() {
        this.saveFile = new File("C:/savefiles/save");
        this.game = new Game();
        this.map = new MyMap();
    }

    /**
     * Reads from terminal, returns input
     * @return String from Scanner of terminal
     */
    public String read() {
        String out;
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        out = sc.nextLine();
        return out;
    }

    /**
     * This is the main game loop.
     * Searches for existing save file which can be then loaded, otherwise starts new game.
     * Starts basic game loop.
     * Prints end text.
     */
    public void start(){
        System.out.println("Welcome to the Maze");
        Date date = new Date();
        System.out.println(date);
        if (saveFile.exists()) {
            System.out.println("NEW  |  LOAD");
            while (true) {
                String s = read();
                if (s.matches("(?i)^load$")) {
                    loadGame();
                    break;
                } else if (s.matches("(?i)^new$")) {
                    newGame();
                    break;
                } else {
                    System.out.println("Wrong input");
                }
            }
        } else {
            newGame();
        }

        map.render(player);

        gameLoop();

        if (player.isDead()) {
            System.out.println("You have died");
        } else if (game.isSuccess()) {
            System.out.println("Congratulations, you've cleared the maze");
        } else {
            System.out.println("Goodbye");
        }
    }

    /**
     * Basic game loop.
     * Prompts for input, sends to handler, executes.
     * Checks if the player is dead, checks if the player has reached the exit
     */
    public void gameLoop() {
        while (!game.ended()){
            System.out.println("move(up/down/left/right), stats, save, exit");
            String in = read();
            game.handleCommand(in);
            map.getTile(player.getPosX(), player.getPosY()).activate(player);
            if (player.isDead()) {
                game.setEnd();
                break;
            } else if (map.getTile(player.getPosX(), player.getPosY()).getTileType() == Tile.Type.EXIT) {
                game.setSuccess();
                game.setEnd();
                break;
            }
        }
    }

    /**
     * Initiates new game.
     * Gets map entrance, places the player there.
     * Scans for input and chooses player class.
     */
    private void newGame() {
        try {
            map.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Map generation problem, exiting");
            game.setEnd();
            return;
        }
        int[] coords = map.getEntrance();

        System.out.println("Select your class\nWARRIOR | ARCHER | THIEF");
        class_select: while (true) {
            String s = read();
            switch (s.toLowerCase(Locale.ENGLISH)) {
                case "warrior":
                    this.player = new Player(Player.PClass.WARRIOR, coords[0], coords[1]);
                    break class_select;
                case "archer":
                    this.player = new Player(Player.PClass.ARCHER, coords[0], coords[1]);
                    break class_select;
                case "thief":
                    this.player = new Player(Player.PClass.THIEF, coords[0], coords[1]);
                    break class_select;
                default:
                    System.out.println("Wrong input");
            }
        }

        ICommand move = new CommandMove(player, map);
        ICommand stats = new CommandStats(player);
        ICommand save = new CommandSave(game, player, map);
        ICommand exit = new CommandExit(game);
        ICommand cheatMap = new CommandCheatMap(map);

        game.addCommand(move);
        game.addCommand(stats);
        game.addCommand(save);
        game.addCommand(exit);
        game.addCommand(cheatMap);

        map.reveal(player);
    }

    /**
     * Loads an old game from a save file, if not ends the game.
     */
    private void loadGame() {
        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Save save = (Save) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Loaded save from: " + save.getDate());
            this.map = save.getMap();
            this.player = save.getPlayer();
            this.game = save.getGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            game.setEnd();
        }
    }
}
