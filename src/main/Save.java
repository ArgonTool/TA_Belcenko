package main;

import java.io.Serializable;

/**
 * Class intended to store data necessary to save the game
 */
public class Save implements Serializable {

    private Game game;
    private Player player;
    private MyMap map;

    /**
     * Constructor
     * @param game game
     * @param player player
     * @param map map
     */
    public Save(Game game, Player player, MyMap map) {
        this.game = game;
        this.player = player;
        this.map = map;
    }

    /**
     * Getter for game
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Getter for player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter for map
     * @return map
     */
    public MyMap getMap() {
        return map;
    }
}
