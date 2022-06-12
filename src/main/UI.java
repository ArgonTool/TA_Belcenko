package main;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class UI {

    private Game game;
    private MyMap map;
    private Player player;
    private boolean end;

    public UI() {
        this.end = false;
    }

    public String read() {
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void start(){
        boolean dead = false;
        boolean success = false;
        System.out.println("Welcome to the Maze");
        if (checkSaves()) {
            System.out.println("NEW  |  LOAD");
            String s = read();
            if(s.toLowerCase(Locale.ENGLISH).equals("load")) {
                loadGame();
            } else {
                newGame();
            }
        } else {
            newGame();
        }

        map.render(player);

        while (!end){
            String in = read();
            game.handleCommand(in);
            map.getTile(player.getPosX(), player.getPosY()).activate(player);
            if (player.isDead()) {
                end = true;
                dead = true;
                break;
            } else if (map.getTile(player.getPosX(), player.getPosY()).getTileType() == Tile.Type.EXIT) {
                success = true;
                end = true;
                break;
            }
        }
        if (dead) {
            System.out.println("You have died\nRESTART (Y/N)?");
            String s = read();
            if (s.toLowerCase(Locale.ENGLISH).equals("y") || s.toLowerCase(Locale.ENGLISH).equals("yes")) {
                end = false;
                start();
            } else if (s.toLowerCase(Locale.ENGLISH).equals("n") || s.toLowerCase(Locale.ENGLISH).equals("no")) {
                System.out.println("Goodbye");
                return;
            } else {
                System.out.println("Interpreting vague answer as NO");
                System.out.println("Goodbye");
            }
        }
        if (success) {
            System.out.println("Congratulations, you've cleared the maze");
        }

    }

    private void newGame() {
        this.map = new MyMap();
        try {
            map.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Map generation problem, exiting");
            end = true;
            return;
        }
        int x=0;
        int y=0;
        for (int i = 0; i < map.getDimensions()[0]; i++) {
            for (int j = 0; j < map.getDimensions()[1]; j++) {
                if (map.getTile(i, j).getTileType() == Tile.Type.ENTRANCE) {
                    x = i;
                    y = j;
                }
            }
        }
        System.out.println("Select your class\nWARRIOR | ARCHER | THIEF");
        class_select: while (true) {
            String s = read();
            switch (s.toLowerCase(Locale.ENGLISH)) {
                case "warrior":
                    this.player = new Player(Player.PClass.WARRIOR, x, y);
                    break class_select;
                case "archer":
                    this.player = new Player(Player.PClass.ARCHER, x, y);
                    break class_select;
                case "thief":
                    this.player = new Player(Player.PClass.THIEF, x, y);
                    break class_select;
                default:
                    System.out.println("Wrong input");
            }
        }

        this.game = new Game();

        ICommand move = new CommandMove(player, map);
        ICommand stats = new CommandStats(player);

        game.addCommand(move);
        game.addCommand(stats);

        map.reveal(player);
    }

    public boolean checkSaves(){
        File f = new File("./src/saved_files/psave.txt");
        return f.exists();
    }

    private void loadGame() {

    }
}
