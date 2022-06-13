package main;

import java.io.Serializable;

/**
 * Class representing individual tiles on a map.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Tile implements Serializable {

    /**
     * Types of tiles
     */
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

    /**
     * Cosntructor
     * @param tileType type of tile
     */
    public Tile(Type tileType) {
        this.tileType = tileType;
        this.explored = false;
    }

    /**
     * Activates a tile, used in children
     * @param player player
     */
    public void activate(Player player) {}

    /**
     * Return whether the tile is hidden, used in children
     * @return false
     */
    public boolean isHidden() {
        return false;
    }

    /**
     * Getter for tileType.
     * @return tileType
     */
    public Type getTileType() {
        return tileType;
    }

    /**
     * Returns the value of explored for rendering.
     * @return explored
     */
    public boolean hasBeenExplored() {
        return explored;
    }

    /**
     * Explores a tile
     */
    public void explore() {
        this.explored = true;
    }
}
