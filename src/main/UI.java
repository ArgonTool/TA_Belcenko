package main;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class UI {

    private Game game;
    private MyMap map;
    private Player player;
    private boolean end;
    private File saveFile;

    public UI() {
        this.end = false;
        this.saveFile = new File("./src/saved_files/gameSave");
    }

    public String read() {
        String out;
        System.out.print("> ");
        Scanner sc = new Scanner(System.in);
        out = sc.nextLine();
        return out;
    }

    public void start(){
        boolean dead = false;
        boolean success = false;
        System.out.println("Welcome to the Maze");
        if (saveFile.exists()) {
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
            System.out.println("move(up/down/left/right), stats, save, exit");
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
            System.out.println("You have died");
        } else if (success) {
            System.out.println("Congratulations, you've cleared the maze");
        } else {
            System.out.println("This should be impossible to reach");
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
        ICommand save = new CommandSave(game, player, map);
        ICommand exit = new CommandExit();

        game.addCommand(move);
        game.addCommand(stats);
        game.addCommand(save);
        game.addCommand(exit);

        map.reveal(player);
    }

    private void loadGame() {
        try {
            FileInputStream fis = new FileInputStream(saveFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Save save = (Save) ois.readObject();
            ois.close();
            fis.close();
            this.map = save.getMap();
            this.player = save.getPlayer();
            this.game = save.getGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            end = true;
        }
    }
}
