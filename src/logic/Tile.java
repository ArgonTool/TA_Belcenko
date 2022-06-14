package logic;

import java.io.Serializable;

/**
 * Class representing individual tiles on a map.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public abstract class Tile implements Serializable {

    /**
     * Boolean for testing purposes
     */
    boolean test;

    /**
     * Types of tiles
     */
    public enum Type{
        TRAP,
        POTION,
        ENTRANCE,
        EXIT,
        NOTHING,
        WALL,
        MONSTER,
        BOSS_MONSTER
    }

    private final Type tileType;

    private boolean explored;

    /**
     * Constructor
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
    public abstract void activate(Player player);

    /**
     * Return whether the tile is hidden, used in children
     * @return false
     */
    public abstract boolean isHidden();

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

    /**
     * Setter for test boolean.
     * @param test value
     */
    public void setTest(boolean test) {
        this.test = test;
    }
}
