package main;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing a Trap tile
 * @author Alexandr Belcenko (bela08)
 * @version 1.0
 */
public class Trap extends Tile{

    boolean hidden;

    /**
     * Trap constructor, tileType is naturally TRAP.
     * Sets the trap as hidden.
     */
    public Trap() {
        super(Type.TRAP);
        this.hidden = true;
    }

    /**
     * After activating the trap has 3 possible traps that can apply with variable damage values.
     * After activating sets hidden value to false after revealing the trap.
     * @param player current player
     */
    @Override
    public void activate(Player player) {
        switch (ThreadLocalRandom.current().nextInt(1,4)) {
            case 1 -> {
                System.out.println("The ground splits underneath you, you fall");
                player.harm(4);
                System.out.println("-4HP" );
            }
            case 2 -> {
                System.out.println("You get splashed by acid from a pipe in the ceiling, you writhe in agony as it eats away at your skin");
                player.harm(3);
                System.out.println("-3HP" );
            }
            case 3 -> {
                System.out.println("You hear a 'thunk' and feel an impact on your neck, you fall unconscious. It seems a rat has deemed to nibble on you while you slept");
                player.harm(2);
                System.out.println("-2HP" );
            }
        }
        this.hidden = false;
    }

    /**
     * Returns whether the trap is hidden.
     * @return boolean hidden
     */
    @Override
    public boolean isHidden() {
        return hidden;
    }
}