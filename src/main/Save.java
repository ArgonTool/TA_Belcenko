package main;

import java.io.Serializable;

public class Save implements Serializable {

    private Game game;
    private Player player;
    private MyMap map;

    public Save(Game game, Player player, MyMap map) {
        this.game = game;
        this.player = player;
        this.map = map;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public MyMap getMap() {
        return map;
    }
}
