package main;

import java.io.Serializable;

/**
 * Implements Serializable.
 * Class representing the player.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Player implements Serializable {

    /**
     * Position of the player.
     */
    private int posX;
    private int posY;

    /**
     * Possible player classes.
     */
    enum PClass {
        WARRIOR,
        ARCHER,
        THIEF
    }

    private final PClass player_class;

    private int hp;
    private int maxHP;
    private int dmg;
    private int sight_range;

    private boolean dead;

    /**
     * Contructor
     * Sets parameters depending on chosen player class.
     * Sets starter coordinates.
     * @param player_class chosen class
     * @param posX beginning X position
     * @param posY beginning Y position
     */
    public Player(PClass player_class, int posX, int posY) {
        this.player_class = player_class;
        this.posX = posX;
        this.posY = posY;
        this.dead = false;
        switch (player_class) {
            case WARRIOR -> {
                this.maxHP = 30;
                this.dmg = 4;
                this.sight_range = 1;
            }
            case ARCHER -> {
                this.maxHP = 25;
                this.dmg = 3;
                this.sight_range = 2;
            }
            case THIEF -> {
                this.maxHP = 20;
                this.dmg = 3;
                this.sight_range = 1;
            }
        }
        this.hp = this.maxHP;
    }

    /**
     * Returns the current class of the player.
     * @return player class
     */
    public PClass getPlayer_class() {
        return player_class;
    }

    /**
     * Returns current HP
     * @return HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns maximum HP
     * @return maximum HP
     */
    public int getMaxHp() {
        return maxHP;
    }

    /**
     * Decreaes the player HP by the amount given.
     * @param damage amount of damage taken by the player.
     */
    public void harm(int damage) {
        hp = hp - damage;
        if (hp <= 0) {
            dead = true;
        }
    }

    /**
     * Restores the player's HP after the use of a potion.
     */
    public void restore() {
        this.hp = maxHP;
    }

    /**
     * Checks if the player is dead.
     * @return boolean dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Retuns the value of current damage output.
     * @return int damage
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Increases current damage by one
     */
    public void increaseDamage() {
        this.dmg++;
    }

    /**
     * Returns player sight range
     * @return sight range
     */
    public int getSight_range() {
        return sight_range;
    }

    /**
     * Returns player's position on the X axis (here UP and DOWN)
     * @return position X
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Moves the player along the X axis
     * @param posX new X position
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Returns player's position on the Y axis (here LEFT and RIGHT)
     * @return position y
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Moves the player along the Y axis
     * @param posY new Y position
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
