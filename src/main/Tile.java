package main;

import java.io.Serializable;

public class Tile implements Serializable {

    enum Type{
        TRAP,
        POTION,
        ENTRANCE,
        EXIT,
        NOTHING,
        WALL,
        MONSTER,
        BOSS_MONSTER
    }

    private Type tileType;

    private boolean explored;

    public Tile(Type tileType) {
        this.tileType = tileType;
        this.explored = false;
    }

    public void activate(Player player) {}

    public boolean isHidden() {
        return false;
    }

    public Type getTileType() {
        return tileType;
    }

    public boolean hasBeenExplored() {
        return explored;
    }

    public void explore() {
        this.explored = true;
    }
}
