package logic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Extends Tile
 * Class to handle monster tiles.
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Monster extends Tile {

    /**
     * Monster types
     */
    public enum MType {
        BLANK,
        SLIME,
        SPIDER,
        GOBLIN,
        BOSS
    }

    private boolean dead;
    private boolean hidden;

    private MType monster_type;
    private int hp;
    private int dmg;

    /**
     * Creates a random monster tile from a selection of three monsters.
     * Assigns HP and DMG accordingly.
     */
    public Monster(MType mtype) {
        super(Type.MONSTER);
        if (mtype == MType.BOSS) {
            this.monster_type = MType.BOSS;
            this.hp = Math.multiplyExact(4, 5);
            this.dmg = 5;
        } else {
            switch (ThreadLocalRandom.current().nextInt(1, 4)) {
                case 1 -> {
                    this.monster_type = MType.SPIDER;
                    this.hp = 7;
                    this.dmg = 5;
                }
                case 2 -> {
                    this.monster_type = MType.SLIME;
                    this.hp = 11;
                    this.dmg = 3;
                }
                case 3 -> {
                    this.monster_type = MType.GOBLIN;
                    this.hp = 16;
                    this.dmg = 2;
                }
            }
        }
        this.dead = false;
        this.hidden = false;
    }

    /**
     * If the monster is already dead quits and does nothing.
     * When activated initiates a battle between the player and this monster.
     * If the tile/monster was not discovered the player is then counted as surprised and the monster then has an attack advantage.
     * @param player current player
     */
    public void activate(Player player) {
        if(isDead()) {
            System.out.println("The body of the monster you've slain has already attracted flies");
            return;
        }
        Battle b = new Battle();
        if (test) {
            b.setTest(true);
        }
        b.battle(player, this, hidden);
        this.hidden = true;
    }

    /**
     * Makes the tile visible on the game map.
     * Player has a 1/5 chance to discover the monster every time they move within sight range.
     * Unless it is a boss, which is always visible
     */
    @Override
    public void explore() {
        super.explore();
        if (monster_type == MType.BOSS) {
            this.hidden = false;
            return;
        }
        if (ThreadLocalRandom.current().nextInt(1, 6)  == 5) {
            this.hidden = false;
        }
    }

    /**
     * Gets the name of this monster based on the monster type.
     * @return String name of monster
     */
    public String getName() {
        String out = "";
        switch (monster_type) {
            case SLIME -> out = "Slime";
            case SPIDER -> out = "Spider";
            case GOBLIN -> out = "Goblin";
            case BOSS -> out = "Boss";
        }
        return out;
    }

    /**
     * Returns whether this monster has been discovered
     * @return boolean discovered
     */
    @Override
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Returns whether the monster is dead or not.
     * @return boolean dead
     */
    public boolean isDead() {
        return dead;
    }

    /***
     * Reduces the HP of the monster by a certain amount and then checks whether it has fallen below 0, if yes then sets boolean "dead" true.
     * @param damage amount by which to reduce hp
     */
    public void harm(int damage){
        this.hp = hp - damage;
        if (hp <= 0) {
            this.dead = true;
        }
    }

    /**
     * Returns current HP
     * @return current HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns this damage
     * @return this damage
     */
    public int getDmg() {
        return dmg;
    }
}
