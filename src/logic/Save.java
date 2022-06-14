package logic;

import java.io.Serializable;
import java.util.Date;

/**
 * Class intended to store data necessary to save the game
 */
public class Save implements Serializable {

    private Game game;
    private Player player;
    private MyMap map;
    private Date date;

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
        this.date = new Date();
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

    /**
     * Getter for date
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
