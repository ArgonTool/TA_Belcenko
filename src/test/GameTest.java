package test;

import logic.*;
import org.junit.Before;
import org.junit.Test;


import java.io.*;

import static org.junit.Assert.*;

public class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private Game game;
    private Player player;
    private MyMap map;

    @Before
    public void setUp() {

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        this.map = new MyMap();
        map.setTest(true);
        try {
            map.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Map generation problem, exiting");
            game.setEnd();
            return;
        }
        int[] coords = map.getEntrance();
        this.player = new Player(Player.PClass.WARRIOR, coords[0], coords[1]);
        this.game = new Game();

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

    public void movement(int i, String s) {
        for (int j = 0; j < i; j++) {
            game.handleCommand("move " + s);
            map.getTile(player.getPosX(), player.getPosY()).activate(player);
        }
        if (player.isDead()) {
            game.setEnd();
        } else if (map.getTile(player.getPosX(), player.getPosY()).getTileType() == Tile.Type.EXIT) {
            game.setSuccess();
            game.setEnd();
        }
    }

    @Test
    public void completionTest() {
        map.reveal(player);
        assertFalse(game.ended());
        assertFalse(game.isSuccess());
        movement(5, "up");
        movement(5, "right");
        movement(2, "up");
        movement(10, "right");
        movement(11, "left");
        movement(1, "up");
        movement(2, "left");
        movement(1, "up");
        movement(1, "left");
        movement(2, "up");
        movement(3, "right");
        movement(4, "right");
        movement(7, "up");
        movement(1, "right");
        movement(1, "up");
        movement(5,"right");
        movement(1, "down");
        movement(2, "right");
        movement(1, "down");
        movement(2, "right");
        movement(4, "down");
        movement(1, "right");
        movement(2, "down");
        movement(1, "down");
        assertTrue(game.ended() && game.isSuccess());
    }

    @Test
    public void movementTest() {
        int x = player.getPosX();
        int y = player.getPosY();
        game.handleCommand("move up");
        assertEquals(x - 1, player.getPosX());
        assertEquals(y, player.getPosY());
        x = player.getPosX();
        y = player.getPosY();
        game.handleCommand("move right");
        assertEquals(x, player.getPosX());
        assertEquals(y, player.getPosY());
    }
    
    @Test
    public void exitTest() {
        game.handleCommand("exit");
        assertTrue(game.ended() && !game.isSuccess());
    }

    @Test
    public void potionTest() {
        player.harm(5);
        assertTrue(player.getHp() < player.getMaxHp());
        Tile potion = new Potion();
        potion.activate(player);
        assertEquals(player.getHp(), player.getMaxHp());
        player.harm(5);
        potion.activate(player);
        assertTrue(player.getHp() < player.getMaxHp());
    }

    @Test
    public void trapTest() {
        Tile trap = new Trap();
        trap.activate(player);
        assertTrue(player.getHp() < player.getMaxHp());
        assertFalse(trap.isHidden());
    }


    @Test
    public void noKeyTest() {
        game.handleCommand("");
        assertEquals("No command entered\r\n", outContent.toString());
    }

    @Test
    public void wrongKeyTest() {
        game.handleCommand("nonsense");
        assertEquals("Unrecognized command: nonsense\r\n", outContent.toString());
    }

    @Test
    public void noParameterTest() {
        game.handleCommand("move");
        assertEquals("No parameter\r\n", outContent.toString());
    }

    @Test
    public void wrongParameterTest() {
        game.handleCommand("move south");
        assertEquals("Wrong parameter\r\n", outContent.toString());
    }

    @Test
    public void fileExistanceTest() {
        File f = new File("C:/savefiles/map.txt");
        assertTrue(f.exists());
    }
}