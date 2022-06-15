package logic;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Implements Serializable
 * Class to handle the map of the game
 * Has a 2d array of Tiles used as a map
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class MyMap implements Serializable {

    private final Tile[][] arr;

    private boolean test;

    public static final int LENGTH = 32;
    public static final int WIDTH = 24;

    static final String nothing = "\u2B1C\uFE0E";
    static final String wall = "\u2B1B\uFE0E";
    static final String monster = "\uD83D\uDFE5";
    static final String potion = "\uD83D\uDFE6";
    static final String player = "\uD83D\uDFE9";
    static final String trap = "\uD83D\uDFE8";
    static final String exit = "\uD83D\uDFEB";
/*
    //Needed for when using a command line
    static final String nothing = "o";
    static final String wall = " ";
    static final String monster = "m";
    static final String potion = "p";
    static final String player = "V";
    static final String trap = "t";
    static final String exit = "e";
*/

    private final int[] entranceCoordinates;

    /**
     * Constructor, creates a new 2d array of Tiles
     */
    public MyMap() {
        this.arr = new Tile[WIDTH][LENGTH];
        this.entranceCoordinates = new int[2];
    }

    /**
     * Loads the map from a .txt file.
     * If no exit or no entrance is found throws new Exception.
     * Logs the coordinates of the entrance.
     * @throws Exception if the File does not exist or the file has no entrance or exit
     */
    public void load() throws Exception {
        File file = new File("C:/savefiles/map.txt");
        Scanner sc = new Scanner(file);
        boolean entrance = false;
        boolean exit = false;
        int i = 0;
        while (sc.hasNextLine()) {
            char[] chars = sc.nextLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                switch (chars[j] - 48){
                    case 0 -> arr[i][j] = new DoNothingTile(Tile.Type.WALL);
                    case 1 -> arr[i][j] = new DoNothingTile(Tile.Type.NOTHING);
                    case 2 -> arr[i][j] = new Potion();
                    case 3 -> arr[i][j] = new Trap();
                    case 4 -> {
                        arr[i][j] = new Monster(Monster.MType.BLANK);
                        if (test) {
                            arr[i][j].setTest(true);
                        }
                    }
                    case 5 -> {
                        arr[i][j] = new DoNothingTile(Tile.Type.EXIT);
                        exit = true;
                    }
                    case 6 -> {
                        arr[i][j] = new DoNothingTile(Tile.Type.ENTRANCE);
                        entranceCoordinates[0] = i;
                        entranceCoordinates[1] = j;
                        entrance = true;
                    }
                    case 7 -> {
                        arr[i][j] = new Monster(Monster.MType.BOSS);
                        if (test) {
                            arr[i][j].setTest(true);
                        }
                    }
                    default -> throw new Exception("Problem with map file, found char:" + chars[j]);
                }
            }
            i++;
        }
        sc.close();
        if (!entrance && !exit) {
            throw new Exception("Entrance or exit missing from map");
        }
    }

    /**
     * Renders the map, prints it into terminal.
     * Hides tiles if they are not marked as discovered or are marked as hidden.
     * @param player for player location
     */
    public void render(Player player) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(i == player.getPosX() && j == player.getPosY()){
                    builder.append(MyMap.player);
                    continue;
                }
                if (arr[i][j].hasBeenExplored()) {
                    switch (arr[i][j].getTileType()) {
                        case WALL -> builder.append(wall);
                        case NOTHING -> builder.append(nothing);
                        case POTION -> builder.append(potion);
                        case TRAP -> {
                            if (arr[i][j].isHidden()){
                                builder.append(nothing);
                            } else {
                                builder.append(trap);
                            }
                        }
                        case MONSTER, BOSS_MONSTER -> {
                            if (arr[i][j].isHidden()) {
                                builder.append(nothing);
                            } else {
                                builder.append(monster);
                            }
                        }
                        case ENTRANCE, EXIT -> builder.append(exit);
                    }
                } else {
                    builder.append(wall);
                }
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    /**
     * Renders the map as is without obscuring any tiles.
     */
    public void render(){
        StringBuilder builder = new StringBuilder();
        for (Tile[] tArr : this.arr) {
            for(Tile t : tArr) {
                switch (t.getTileType()) {
                    case WALL -> builder.append(wall);
                    case NOTHING -> builder.append(nothing);
                    case POTION -> builder.append(potion);
                    case TRAP -> builder.append(trap);
                    case MONSTER, BOSS_MONSTER -> builder.append(monster);
                    case ENTRANCE, EXIT -> builder.append(exit);
                }
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    /**
     * Reveals/Explores tiles based on player location.
     * @param player for location
     */
    public void reveal(Player player) {
        int x = player.getPosX();
        int y = player.getPosY();
        int sr = player.getSight_range();

        for (int i = -sr; i <= sr ; i++) {
            if (x+i <= WIDTH - 1 && x+i >= 0) {
                arr[x + i][y].explore();
            }
            if (y+i <= LENGTH - 1 && y+i >= 0) {
                arr[x][y+i].explore();
            }
        }
    }

    /**
     * Return tiles at coordinates x, y.
     * @param x coordinate
     * @param y coordinate
     * @return tile
     */
    public Tile getTile(int x, int y) {
        return arr[x][y];
    }

    /**
     * Returns map dimensions as an int array
     * @return map dimensions
     */
    public int[] getDimensions(){
        return new int[]{WIDTH, LENGTH};
    }

    /**
     * Returns entrance coordinates
     * @return entranceCoordinates
     */
    public int[] getEntrance() {
        return entranceCoordinates;
    }

    /**
     * Sets test boolean
     * @param test value of boolean
     */
    public void setTest(boolean test) {
        this.test = test;
    }
}
