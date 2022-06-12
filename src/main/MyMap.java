package main;

import java.io.*;
import java.util.Scanner;

public class MyMap implements Serializable {

    private Tile[][] arr;

    public static final int LENGTH = 32;
    public static final int WIDTH = 24;

    static final String white = "\u2B1C\uFE0E";
    static final String black = "\u2B1B\uFE0E";
    static final String red = "\uD83D\uDFE5";
    static final String blue = "\uD83D\uDFE6";
    static final String green = "\uD83D\uDFE9";
    static final String yellow = "\uD83D\uDFE8";
    static final String brown = "\uD83D\uDFEB";

    public MyMap() {
        this.arr = new Tile[WIDTH][LENGTH];
    }

    public void load() throws Exception {
        File file = new File("./src/saved_files/map.txt");
        Scanner sc = new Scanner(file);
        boolean entrance = false;
        boolean exit = false;
        int i = 0;
        while (sc.hasNextLine()) {
            char[] chars = sc.nextLine().toCharArray();
            for (int j = 0; j < chars.length; j++) {
                switch (chars[j] - 48){
                    case 0 -> arr[i][j] = new Tile(Tile.Type.WALL);
                    case 1 -> arr[i][j] = new Tile(Tile.Type.NOTHING);
                    case 2 -> arr[i][j] = new Potion();
                    case 3 -> arr[i][j] = new Trap();
                    case 4 -> arr[i][j] = new Monster(Monster.MType.BLANK);
                    case 5 -> {
                        arr[i][j] = new Tile(Tile.Type.EXIT);
                        exit = true;
                    }
                    case 6 -> {
                        arr[i][j] = new Tile(Tile.Type.ENTRANCE);
                        entrance = true;
                    }
                    case 7 -> arr[i][j] = new Monster(Monster.MType.BOSS);
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

    public void render(Player player) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if(i == player.getPosX() && j == player.getPosY()){
                    builder.append(green);
                    continue;
                }
                if (arr[i][j].hasBeenExplored()) {
                    switch (arr[i][j].getTileType()) {
                        case WALL -> builder.append(black);
                        case NOTHING -> builder.append(white);
                        case POTION -> builder.append(blue);
                        case TRAP -> {
                            if (arr[i][j].isHidden()){
                                builder.append(white);
                            } else {
                                builder.append(yellow);
                            }
                        }
                        case MONSTER, BOSS_MONSTER -> {
                            if (arr[i][j].isHidden()) {
                                builder.append(white);
                            } else {
                                builder.append(red);
                            }
                        }
                        case ENTRANCE, EXIT -> builder.append(brown);
                    }
                } else {
                    builder.append(black);
                }
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

    public void render(){
        StringBuilder builder = new StringBuilder();
        for (Tile[] tArr : this.arr) {
            for(Tile t : tArr) {
                switch (t.getTileType()) {
                    case WALL -> builder.append(black);
                    case NOTHING -> builder.append(white);
                    case POTION -> builder.append(blue);
                    case TRAP -> builder.append(yellow);
                    case MONSTER, BOSS_MONSTER -> builder.append(red);
                    case ENTRANCE, EXIT -> builder.append(brown);
                }
            }
            builder.append('\n');
        }
        System.out.println(builder);
    }

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

    public Tile getTile(int x, int y) {
        return arr[x][y];
    }

    public int[] getDimensions(){
        return new int[]{WIDTH, LENGTH};
    }


}
